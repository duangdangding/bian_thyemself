package com.lshs.sbtemf.controller;

import com.lshs.sbtemf.entry.BiAn;
import com.lshs.sbtemf.entry.Contains;
import com.lshs.sbtemf.entry.EUDataGridResult;
import com.lshs.sbtemf.entry.QueryEntry;
import com.lshs.sbtemf.service.BianService;
import com.lshs.sbtemf.utils.JsoupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 彼岸图
 * @author: LuShao
 * @create: 2018-08-23 17:09
 **/
@RestController
@RequestMapping("/main/")
public class FigureCtr {

	@Autowired
	private BianService bianService;

	/**
	 * 查询
	 * @param queryEntry
	 * @return
	 */
	@RequestMapping("all")
	public EUDataGridResult getCategoryQuery(QueryEntry queryEntry){
		EUDataGridResult imgs = bianService.findImgs(queryEntry);
		return imgs;
	}

	/**
	 * 查询当前分辨率的最后一条
	 * @param spx
	 * @return
	 */
	/*@RequestMapping("last")
	public BiAn lastData(String spx){
		BiAn biAn = bianService.lastData(spx);
		return biAn;
	}*/

	/**
	 * 查询当前分辨率的最新
	 * @param spx
	 * @return
	 */
	@RequestMapping("first")
	public BiAn firstData(String spx){
		BiAn biAn = bianService.firstData(spx);
		return biAn;
	}

	/**
	 * 下载图片
	 * @param ids
	 * @return
	 */
	@RequestMapping("download")
	public Map<String,Object> download(String[] ids){
		Map<String,Object> map=new HashMap<>();
		try {
			String format = Contains.SDF1.format(new Date());
			File file=new File(Contains.DIR+format);
			if(!file.exists()) file.mkdirs();
			for (String img : ids) {
				if(img.contains(".jpg")){
					URLConnection conn=new URL(img).openConnection();
					InputStream is = conn.getInputStream();
					String imgname = img.substring(img.lastIndexOf("/") + 1);
					BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file+File.separator+imgname));
					byte[] bytes=new byte[1024*1024];
					int size;
					while (-1!=(size=is.read(bytes))){
						bos.write(bytes,0,size);
					}
					bos.close();
					is.close();
				}
			}
			map.put("status",200);
		}catch (Exception e){
			map.put("status",500);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 插入新数据
	 * @param queryEntry
	 * @return
	 */
	@RequestMapping("add")
	public Map<String,Object> add(QueryEntry queryEntry){
		Map<String,Object> map=new HashMap<>();
		String spx=queryEntry.getScreen();
		Integer page = queryEntry.getPage();
		String url="http://www.netbian.com/"+spx+"/";
		if (page==1) url=url+"index.htm";
		else url=url+"index_" + page +".htm";
		try {
			int status = JsoupUtil.getStatus(url);
			if(status==200){
				BiAn biAn=new BiAn();
				biAn.setPage(page);
				biAn.setFromurl(url);
				biAn.setScreen(spx);
				List<BiAn> url1s = JsoupUtil.getUrl1List(url, biAn,queryEntry.getStart(),queryEntry.getEnd());
				int size=0;
				for (BiAn biAn1 : url1s) {
					String url1 = biAn1.getUrl1();
					if (url1.contains(".htm")){
						int status1 = JsoupUtil.getStatus(url1);
						if(status1==200){
							BiAn biAn2 = JsoupUtil.getUrl2(url1, biAn1);
							String url2 = biAn2.getUrl2();
							if (url2.contains(".htm")){
								int status2 = JsoupUtil.getStatus(url2);
								if (status2==200){
									BiAn biAn3 = JsoupUtil.getUrl3(url2, biAn2);
									if (biAn3.getUrl3().contains(".jpg")){
										int i1 = bianService.saveBian(biAn3);
										size+=i1;
									}
								}
							}
						}
					}
				}
				if (size>0) map.put("status",200);
			}else map.put("status",404);
		} catch (Exception e) {
			map.put("status",500);
			e.printStackTrace();
		}
		return map;
	}

}

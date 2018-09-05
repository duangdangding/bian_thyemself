package com.lshs.sbtemf.test;

import com.lshs.sbtemf.SbtemfApplication;
import com.lshs.sbtemf.entry.Contains;
import com.lshs.sbtemf.entry.Mbpaper;
import com.lshs.sbtemf.entry.Wptemp;
import com.lshs.sbtemf.service.MbpaperService;
import com.lshs.sbtemf.service.WptempService;
import com.lshs.sbtemf.utils.JsoupUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-01 11:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbtemfApplication.class)
public class PhoneTest {

	@Autowired
	private WptempService wptempService;

	@Autowired
	private MbpaperService mbpaperService;

	//http://www.win4000.com/mobile_2338_0_106_1.html
	//http://www.win4000.com/mobile_2338_0_120_1.html
	//http://www.win4000.com/mobile_2362_0_106_1.html
	//http://www.win4000.com/mobile_2362_0_119_1.html

	private static int count=0;
	public void setUrlList(String url) throws IOException {
		Document document = Jsoup.connect(url).timeout(300000).get();
		Elements element = document.getElementsByClass("Left_list_cont").get(0).getElementsByClass("clearfix");
		//System.err.println("element~~"+element.size());
		Elements lis = element.get(0).getElementsByTag("li");
		List<Mbpaper> list=new ArrayList<>();
		for (Element li : lis) {
			Wptemp wptemp=new Wptemp();
			Element a = li.getElementsByTag("a").get(0);
			String href = a.attr("href");
			wptemp.setUrl(href);
			int add = wptempService.add(wptemp);
			count++;
		}
	}

	@Test
	public void demo6() throws IOException, URISyntaxException {
		System.err.println("开始保存~~~");
		for (int i = 1; i < 6; i++) {
			String url="http://www.win4000.com/mobile_0_0_0_"+i+".html";
			int status = JsoupUtil.getStatus(url);
			if (status==200){
				setUrlList(url);
			}
		}
		System.err.println("保存完成~~~"+count);
	}

	/**
	 * 设置mbpaper url1 title img1
	 * @param url
	 * @param mbpaper
	 * @throws IOException
	 */
	public List<Mbpaper> getUrl1(String url) throws IOException {
		Document document = Jsoup.connect(url).timeout(30000).get();
		List<Mbpaper> lists=new ArrayList<>();
		//Element list = document.getElementById("scroll");
		Element element = document.getElementsByClass("scroll-img02").get(0);
		Elements lis = element.getElementsByTag("li");
		for (Element li : lis) {
			Mbpaper mbpaper=new Mbpaper();
			Element a = li.getElementsByTag("a").get(0);
			String href = a.attr("href");
			mbpaper.setUrl1(href);
			Element img = a.getElementsByTag("img").get(0);
			String src = img.attr("data-original");
			mbpaper.setWpimg1(src);
			String title = img.attr("title");
			mbpaper.setWptitle(title);
			lists.add(mbpaper);
		}
		return lists;
	}

	/**
	 * 把数据添加进数据库
	 * @param url
	 * @param mbpaper
	 * @throws IOException
	 */
	public void add(String url,Mbpaper mbpaper) throws IOException {
		Document document = Jsoup.connect(url).timeout(30000).get();
		Element a = document.getElementsByClass("breadcrumbs").get(0).getElementsByTag("a").get(2);
		String category = a.text();
		mbpaper.setWpcategory(category);
		Element bigimg = document.getElementsByClass("Bigimg_style").get(0);
		Element time = bigimg.getElementsByClass("time").get(0);
		String text = time.text();
		mbpaper.setUpdatetime(text);
		Element em = bigimg.getElementsByClass("size").get(0).getElementsByTag("em").get(0);
		String spx = em.text();
		mbpaper.setWpscreen(spx);
		Element byId = document.getElementById("pic-meinv");
		Element imglarge = byId.getElementsByClass("pic-large").get(0);
		String src = imglarge.attr("src");
		mbpaper.setWpimg2(src);
		mbpaper.setWpaddtime(Contains.SDF3.format(new Date()));
		System.err.println("最后的~~~"+mbpaper);

		mbpaperService.add(mbpaper);
		count++;
	}

	@Test
	public void urlList() throws IOException, URISyntaxException {
		List<Wptemp> select = wptempService.select();
		for (Wptemp wptemp : select) {
			String url = wptemp.getUrl();
			//int status = JsoupUtil.getStatus(url);
			if (JsoupUtil.getStatus(url)==200){
			//	调用的方法
				System.err.println("urlList~~~url="+url);
				List<Mbpaper> url1s = getUrl1(url);
				for (Mbpaper mbpaper : url1s) {
					String url1 = mbpaper.getUrl1();
					if(JsoupUtil.getStatus(url1)==200){
					//
						add(url1,mbpaper);
					}
				}
			}
		}
		System.err.println("共保存数据："+count);
	}

	@Test
	public void demo110(){
		int delete = wptempService.delete();
		System.err.println("删除了~~~"+delete);
	}

	@Test
	public void demo111(){
		int delete = mbpaperService.delete();
		System.err.println("删除了~~~"+delete);
	}

	@Test
	public void demo112() throws IOException {
		String url="http://www.win4000.com/mobile_detail_150139.html";
		Document document = Jsoup.connect(url).get();
		System.err.println(document);
	}
}

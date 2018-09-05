package com.lshs.sbtemf.controller;

import com.github.pagehelper.Page;
import com.lshs.sbtemf.entry.BiAn;
import com.lshs.sbtemf.entry.EUDataGridResult;
import com.lshs.sbtemf.entry.Mbpaper;
import com.lshs.sbtemf.entry.QueryEntry;
import com.lshs.sbtemf.service.BianService;
import com.lshs.sbtemf.service.MbpaperService;
import com.lshs.sbtemf.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-08-23 14:04
 **/
@Controller
public class PageCtr {

	@Autowired
	private BianService bianService;

	@Autowired
	private MbpaperService mbpaperService;

	@RequestMapping("/")
	public String toPage(Map<String,Object> map, QueryEntry queryEntry){
		EUDataGridResult imgs = bianService.findImgs(queryEntry);
		List<BiAn> rows = (List<BiAn>) imgs.getRows();
		PageBean<BiAn> pageBean=new PageBean<BiAn>(queryEntry.getPage()+"",(int)imgs.getTotal(),queryEntry.getRows(),rows);
		map.put("bians",pageBean);
		map.put("entry",queryEntry);
		map.put("home","");
		return "index";
	}

	@RequestMapping("/pages/{abc}")
	public String toPage(@PathVariable("abc") String abc,Map<String,Object> map, QueryEntry queryEntry){
		String type = queryEntry.getType();
		if (type!=null&&type.equals("phone")){
			EUDataGridResult byCase = mbpaperService.findByCase(queryEntry);
			List<Mbpaper> rows = (List<Mbpaper>) byCase.getRows();
			PageBean<Mbpaper> pageBean=new PageBean<Mbpaper>(queryEntry.getPage()+"",(int)byCase.getTotal(),queryEntry.getRows(),rows);
			map.put("phones",pageBean);
			map.put("phoneQu",queryEntry);
			map.put("home","pages/phone");
		}
		//System.err.println(type+"~~~~~~~~~~~~~"+abc);
		return abc;
	}

}

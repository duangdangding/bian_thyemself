package com.lshs.sbtemf.controller;

import com.lshs.sbtemf.entry.Screens;
import com.lshs.sbtemf.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-08-25 15:24
 **/
//@Controller
	@RestController
@RequestMapping("/scr/")
public class ScreenCtr {

	@Autowired
	private ScreenService screenService;

	@RequestMapping("all")
	public List<Screens> findAll(){
		List<Screens> list = screenService.findAll();
		return list;
	}

	@RequestMapping("add")
	public Map<String,Object> add(Screens screens){
		Map<String,Object> map=new HashMap<>();
		int save = screenService.save(screens);
		if (save>0) map.put("status",200);
		return map;
	}

	@RequestMapping("del")
	public Map<String,Object> delete(Integer[] ids){
		Map<String,Object> map=new HashMap<>();
		int save = screenService.delete(ids);
		if (save>0) map.put("status",200);
		return map;
	}
}

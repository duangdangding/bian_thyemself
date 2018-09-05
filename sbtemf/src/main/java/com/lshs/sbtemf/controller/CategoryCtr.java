package com.lshs.sbtemf.controller;

import com.lshs.sbtemf.entry.Category;
import com.lshs.sbtemf.service.CategoryService;
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
@RestController
@RequestMapping("/c/")
public class CategoryCtr {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("all")
	public List<Category> findAll(){
		List<Category> list = categoryService.findAll();
		return list;
	}

	@RequestMapping("add")
	public Map<String,Object> add(Category category){
		Map<String,Object> map=new HashMap<>();
		int save = categoryService.save(category);
		if (save>0) map.put("status",200);
		return map;
	}

	@RequestMapping("del")
	public Map<String,Object> delete(Integer[] ids){
		Map<String,Object> map=new HashMap<>();
		int save = categoryService.delete(ids);
		if (save>0) map.put("status",200);
		return map;
	}

//	@RequestMapping("pageAll")
//	@ResponseBody
//	public List<Category> pageAll(){
//		List<Category> list = categoryService.findAll();
//		return list;
//	}


}

package com.lshs.sbtemf.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lshs.sbtemf.conf.datasource.DS;
import com.lshs.sbtemf.entry.Category;
import com.lshs.sbtemf.entry.Contains;
import com.lshs.sbtemf.mapper.CategoryMapper;
import com.lshs.sbtemf.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-08-24 10:23
 **/
@Service
public class CategoryServiceI implements CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	@DS("bian")
	public int save(Category category) {
		
		int i = categoryMapper.insertSelective(category);
		return i;
	}

	@Override
	@DS("bian")
	public int delete(Integer[] ids) {
		return categoryMapper.delete(ids);
	}

	@Override
	@DS("bian")
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		if (redisUtil.hasKey(Contains.CATEGORYLIST)){
			System.err.println("从redis中查询。。。。");
			Object o = redisUtil.get(Contains.CATEGORYLIST);
			list = JSONArray.parseArray((String) o, Category.class);
		}else {
			list = categoryMapper.findAll();
			String s = JSON.toJSONString(list);
			redisUtil.set(Contains.CATEGORYLIST,s,600);
		}
		return list;
	}
}

package com.lshs.sbtemf.service;


import com.lshs.sbtemf.entry.Category;

import java.util.List;

/**
 * @Description: 定义接口
 * @author: LuShao
 * @create: 2018-08-24 10:22
 **/
public interface CategoryService {

	int save(Category category);

	int delete(Integer[] ids);

	List<Category> findAll();
}

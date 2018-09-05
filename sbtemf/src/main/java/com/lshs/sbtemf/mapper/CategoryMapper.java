package com.lshs.sbtemf.mapper;


import com.lshs.sbtemf.entry.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

	List<Category> findAll();

	int delete(Integer[] ids);
}
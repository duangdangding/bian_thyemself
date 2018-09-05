package com.lshs.sbtemf.mapper;

import com.lshs.sbtemf.entry.Screens;

import java.util.List;

public interface ScreensMapper {
    int deleteByPrimaryKey(Integer screenid);

    int insert(Screens record);

    int insertSelective(Screens record);

    Screens selectByPrimaryKey(Integer screenid);

    int updateByPrimaryKeySelective(Screens record);

    int updateByPrimaryKey(Screens record);

    List<Screens> findAll();

	int delete(Integer[] ids);
}
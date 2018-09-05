package com.lshs.sbtemf.service;


import com.lshs.sbtemf.entry.Screens;

import java.util.List;

/**
 * @Description: 定义接口
 * @author: LuShao
 * @create: 2018-08-24 10:22
 **/
public interface ScreenService {

	int save(Screens screens);

	List<Screens> findAll();

	int delete(Integer[] ids);
}

package com.lshs.sbtemf.service;

import com.lshs.sbtemf.entry.Wptemp;

import java.util.List;

/**
 * @Description: 定义接口
 * @author: LuShao
 * @create: 2018-09-01 12:17
 **/
public interface WptempService {
	int add(Wptemp wptemp);
	int delete();
	List<Wptemp> select();
}

package com.lshs.sbtemf.service;

import com.lshs.sbtemf.entry.EUDataGridResult;
import com.lshs.sbtemf.entry.Mbpaper;
import com.lshs.sbtemf.entry.QueryEntry;

import java.util.List;

/**
 * @Description: 定义接口
 * @author: LuShao
 * @create: 2018-09-01 16:30
 **/
public interface MbpaperService {

	int add(Mbpaper mbpaper);

	EUDataGridResult findByCase(QueryEntry queryEntry);
	int delete();

}

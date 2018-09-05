package com.lshs.sbtemf.service;

import com.lshs.sbtemf.entry.BiAn;
import com.lshs.sbtemf.entry.EUDataGridResult;
import com.lshs.sbtemf.entry.QueryEntry;

/**
 * @Description: 定义接口
 * @author: LuShao
 * @create: 2018-08-24 10:22
 **/
public interface BianService {

	int saveBian(BiAn biAn);

	int deleteBian();

//	BiAn lastData(String spx);
	BiAn firstData(String spx);

	EUDataGridResult findImgs(QueryEntry queryEntry);

}

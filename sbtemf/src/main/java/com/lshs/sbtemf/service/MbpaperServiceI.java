package com.lshs.sbtemf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lshs.sbtemf.conf.datasource.DS;
import com.lshs.sbtemf.entry.EUDataGridResult;
import com.lshs.sbtemf.entry.Mbpaper;
import com.lshs.sbtemf.entry.QueryEntry;
import com.lshs.sbtemf.mapper.MbpaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-01 16:30
 **/
@Service
public class MbpaperServiceI implements MbpaperService {

	@Autowired
	private MbpaperMapper mbpaperMapper;

	@Override
	@DS("bian")
	public int add(Mbpaper mbpaper) {
		return mbpaperMapper.add(mbpaper);
	}

	@Override
	@DS("bian")
	public EUDataGridResult findByCase(QueryEntry queryEntry) {
		PageHelper.startPage(queryEntry.getPage(),queryEntry.getRows());
		List<Mbpaper> byCase = mbpaperMapper.findByCase(queryEntry);
		PageInfo<Mbpaper> info=new PageInfo<>(byCase);
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(byCase);
		result.setTotal(info.getTotal());
		return result;
	}

	@Override
	@DS("bian")
	public int delete() {
		return mbpaperMapper.delete();
	}
}

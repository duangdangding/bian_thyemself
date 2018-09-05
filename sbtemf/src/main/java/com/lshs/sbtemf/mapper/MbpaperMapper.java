package com.lshs.sbtemf.mapper;

import com.lshs.sbtemf.entry.Mbpaper;
import com.lshs.sbtemf.entry.QueryEntry;

import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-01 11:45
 **/
public interface MbpaperMapper {

	int add(Mbpaper mbpaper);

	List<Mbpaper> findByCase(QueryEntry queryEntry);

	int delete();
}

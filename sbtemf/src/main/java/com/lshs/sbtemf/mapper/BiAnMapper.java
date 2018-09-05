package com.lshs.sbtemf.mapper;


import com.lshs.sbtemf.entry.BiAn;
import com.lshs.sbtemf.entry.QueryEntry;

import java.util.List;

public interface BiAnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BiAn record);

    int insertSelective(BiAn record);

    BiAn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BiAn record);

    int updateByPrimaryKey(BiAn record);

    List<BiAn> findByCase(QueryEntry queryEntry);

	int deleteBian();

//	List<BiAn> lastData(String spx);
	List<BiAn> firstData(String spx);
}
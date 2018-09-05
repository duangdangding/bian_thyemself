package com.lshs.sbtemf.service;

import com.lshs.sbtemf.conf.datasource.DS;
import com.lshs.sbtemf.entry.Wptemp;
import com.lshs.sbtemf.mapper.WptempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-01 12:17
 **/
@Service
public class WptempServiceI implements WptempService {

	@Autowired
	private WptempMapper wptempMapper;

	@Override
	@DS("bian")
	public int add(Wptemp wptemp) {
		int add = wptempMapper.add(wptemp);
		return add;
	}

	@Override
	@DS("bian")
	public int delete() {
		return wptempMapper.delete();
	}

	@Override
	@DS("bian")
	public List<Wptemp> select() {
		return wptempMapper.select();
	}
}

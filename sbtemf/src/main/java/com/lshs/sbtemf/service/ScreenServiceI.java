package com.lshs.sbtemf.service;

import com.lshs.sbtemf.conf.datasource.DS;
import com.lshs.sbtemf.entry.Screens;
import com.lshs.sbtemf.mapper.ScreensMapper;
import com.lshs.sbtemf.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-08-24 10:23
 **/
@Service
public class ScreenServiceI implements ScreenService {

	@Autowired
	private ScreensMapper screensMapper;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	@DS("bian")
	public int save(Screens screens) {
		int i = screensMapper.insertSelective(screens);
		return i;
	}

	@Override
	@DS("bian")
	public List<Screens> findAll() {
		List<Screens> list = new ArrayList<>();
		/*if (redisUtil.hasKey(Contains.SCREENSLIST)){
			System.err.println("从redis中查询。。。。");
			list= redisUtil.lGet(Contains.SCREENSLIST, 0, -1);
		}else {
			list = screensMapper.findAll();
			redisUtil.lset(Contains.SCREENSLIST,list,600);
		}*/
		list = screensMapper.findAll();
		//System.err.println("Screens~~~"+list);
		return list;
	}

	@Override
	@DS("bian")
	public int delete(Integer[] ids) {
		return screensMapper.delete(ids);
	}
}

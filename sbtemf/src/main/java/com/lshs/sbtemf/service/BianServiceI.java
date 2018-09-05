package com.lshs.sbtemf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lshs.sbtemf.conf.datasource.DS;
import com.lshs.sbtemf.entry.BiAn;
import com.lshs.sbtemf.entry.Contains;
import com.lshs.sbtemf.entry.EUDataGridResult;
import com.lshs.sbtemf.entry.QueryEntry;
import com.lshs.sbtemf.mapper.BiAnMapper;
import com.lshs.sbtemf.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-08-24 10:23
 **/
@Service
public class BianServiceI implements BianService {

	@Autowired
	private BiAnMapper biAnMapper;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	@DS("bian")
	public int saveBian(BiAn biAn) {
		String format = Contains.SDF2.format(new Date());
		biAn.setAddtime(format);
		int i = biAnMapper.insertSelective(biAn);
		return i;
	}

	@Override
	@DS("bian")
	public int deleteBian() {
		int i = biAnMapper.deleteBian();
		return i;
	}

	/*@Override
	public BiAn lastData(String spx) {
		return biAnMapper.lastData(spx).get(0);
	}*/

	@Override
	@DS("bian")
	public BiAn firstData(String spx) {
		BiAn biAn = new BiAn();
		/*if (redisUtil.hasKey("biAn")){
			biAn= (BiAn) redisUtil.get("biAn");
		}else {
			biAn = biAnMapper.firstData(spx).get(0);
			redisUtil.set("biAn",biAn,600);
		}*/
		biAn = biAnMapper.firstData(spx).get(0);
		return biAn;
	}

	/**
	 *
	 * @param page
	 * @param rows
	 * @param queryEntry
	 * @return
	 */
	@Override
	@DS("bian")
	public EUDataGridResult findImgs(QueryEntry queryEntry){
		PageHelper.startPage(queryEntry.getPage(), queryEntry.getRows());
		List<BiAn> list = biAnMapper.findByCase(queryEntry);
		PageInfo<BiAn> info=new PageInfo<>(list);
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}


}

package com.lshs.sbtemf.test;

import org.junit.Test;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-04 14:13
 **/
public class MyTest {

	@Test
	public void demo1(){
		String data1="[[Category{categoryid=2, categoryname='风景'}, Category{categoryid=3, categoryname='美女'}, Category{categoryid=4, categoryname='日历'}, Category{categoryid=5, categoryname='游戏'}, Category{categoryid=6, categoryname='动漫'}, Category{categoryid=7, categoryname='动态'}, Category{categoryid=8, categoryname='唯美'}, Category{categoryid=9, categoryname='设计'}, Category{categoryid=10, categoryname='可爱'}, Category{categoryid=11, categoryname='汽车'}, Category{categoryid=12, categoryname='花卉'}, Category{categoryid=13, categoryname='动物'}, Category{categoryid=14, categoryname='节日'}, Category{categoryid=15, categoryname='人物'}, Category{categoryid=16, categoryname='美食'}, Category{categoryid=17, categoryname='水果'}, Category{categoryid=18, categoryname='建筑'}, Category{categoryid=19, categoryname='影视'}, Category{categoryid=20, categoryname='体育'}, Category{categoryid=21, categoryname='军事'}, Category{categoryid=22, categoryname='非主流'}, Category{categoryid=23, categoryname='其他'}, Category{categoryid=24, categoryname='王者荣耀'}, Category{categoryid=25, categoryname='护眼'}, Category{categoryid=26, categoryname='鬼刀'}]]";
		//Object parse1 = JSONObject.parse(data1);
		//JSONObject parse = (JSONObject) JSON.parse(data1);
		//JSONObject data = parse.getJSONArray("data").getJSONObject(0);
		//Category category = JSON.toJavaObject(data, Category.class);
		//System.out.println(category);
		String format = String.format(data1);
		System.err.println(format);
	}
}

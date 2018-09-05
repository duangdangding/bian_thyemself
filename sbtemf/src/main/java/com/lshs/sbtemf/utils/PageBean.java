package com.lshs.sbtemf.utils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 使用泛型  使之成为一个通用类
 * @author Administrator
 *封装判断
 * @param <T>
 */
public class PageBean<T> {
	public final static int SHOWDATANUM=54;
	private String page;//当前页
	private int allData;//总记录数
	private int totalMsg;//每页显示的条数
	private List<T> list;//存放对象的list
	public PageBean() {super();}
	public PageBean(String page, int allData, int totalMsg,List<T> list) {
		super();
		this.page = page;
		this.allData = allData;
		this.totalMsg = totalMsg;
		this.list = list;
	}
	/**
	 * 判断当前页
	 * @return 规则的page
	 */
	public String getPage() {
		return page!=null&&page.trim().length()>0? page:"1";
	}
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * 获得当前页
	 * @return 返回可接受的curPage
	 */
	public int getCurPage(){
		if(Pattern.compile("[0-9]*").matcher(getPage()).matches()){
			int curPage = Integer.valueOf(getPage());
			return curPage>=1&&curPage<=getAllPage()? curPage:1;
		}
		return 1;
	}
	/**
	 * 当前页的第一条数据
	 * @return
	 */
	public int getCurPageFirst(){
		return getCurPage()>1? (getCurPage()-1)*totalMsg:0;
	}
	//总记录数
	public int getAllData() {
		return allData;
	}
	//总记录数
	public void setAllData(int allData) {
		this.allData = allData;
	}
	//每页显示的条数
	public int getTotalMsg() {
		return totalMsg;
	}
	//每页显示的条数
	public void setTotalMsg(int totalMsg) {
		this.totalMsg = totalMsg;
	}
	/**
	 * 获得总页数
	 * @return
	 */
	public int getAllPage() {
		if (allData<totalMsg) return 1;
		int page=allData/totalMsg;
		return allData%totalMsg==0? page:page+1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"page='" + page + '\'' +
				", allData=" + allData +
				", totalMsg=" + totalMsg +
				", list=" + list +
				'}';
	}
}

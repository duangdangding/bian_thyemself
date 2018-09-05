package com.lshs.sbtemf.entry;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-01 11:42
 **/
public class Mbpaper {
	private Integer wpid;
	private String wptitle;
	private String updatetime;
	private String wpimg1;
	private String wpimg2;
	private String wpscreen;
	private String wpcategory;
	private String wpaddtime;

	private String url1;

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public Integer getWpid() {
		return wpid;
	}

	public void setWpid(Integer wpid) {
		this.wpid = wpid;
	}

	public String getWptitle() {
		return wptitle;
	}

	public void setWptitle(String wptitle) {
		this.wptitle = wptitle;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getWpimg1() {
		return wpimg1;
	}

	public void setWpimg1(String wpimg1) {
		this.wpimg1 = wpimg1;
	}

	public String getWpimg2() {
		return wpimg2;
	}

	public void setWpimg2(String wpimg2) {
		this.wpimg2 = wpimg2;
	}

	public String getWpscreen() {
		return wpscreen;
	}

	public void setWpscreen(String wpscreen) {
		this.wpscreen = wpscreen;
	}

	public String getWpcategory() {
		return wpcategory;
	}

	public void setWpcategory(String wpcategory) {
		this.wpcategory = wpcategory;
	}

	public String getWpaddtime() {
		return wpaddtime;
	}

	public void setWpaddtime(String wpaddtime) {
		this.wpaddtime = wpaddtime;
	}

	@Override
	public String toString() {
		return "Mbpaper{" +
				"wpid=" + wpid +
				", wptitle='" + wptitle + '\'' +
				", updatetime='" + updatetime + '\'' +
				", wpimg1='" + wpimg1 + '\'' +
				", wpimg2='" + wpimg2 + '\'' +
				", wpscreen='" + wpscreen + '\'' +
				", wpcategory='" + wpcategory + '\'' +
				", wpaddtime='" + wpaddtime + '\'' +
				", url1='" + url1 + '\'' +
				'}';
	}
}

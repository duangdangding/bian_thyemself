package com.lshs.sbtemf.entry;

public class Screens {
    private Integer screenid;

    private String screenname;

    public Integer getScreenid() {
        return screenid;
    }

    public void setScreenid(Integer screenid) {
        this.screenid = screenid;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname == null ? null : screenname.trim();
    }

	@Override
	public String toString() {
		return "Screens{" +
				"screenid=" + screenid +
				", screenname='" + screenname + '\'' +
				'}';
	}
}
package com.lshs.sbtemf.entry;

public class Category {
    private Integer categoryid;

    private String categoryname;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

	@Override
	public String toString() {
		return "Category{" +
				"categoryid=" + categoryid +
				", categoryname='" + categoryname + '\'' +
				'}';
	}
}
package com.lshs.sbtemf.entry;

public class BiAn {
    private Integer id;

    private String title;

    private String url1;

    private String url2;

    private String url3;

    private String screen;

    private String category;

    private String img1;

    private String img2;

    private String updatetime;

    private Integer page;

    private String fromurl;

    private Integer imgNum;

    private String addtime;

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getImgNum() {
		return imgNum;
	}

	public void setImgNum(Integer imgNum) {
		this.imgNum = imgNum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1 == null ? null : url1.trim();
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2 == null ? null : url2.trim();
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3 == null ? null : url3.trim();
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen == null ? null : screen.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2 == null ? null : img2.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getFromurl() {
        return fromurl;
    }

	@Override
	public String toString() {
		return "BiAn{" +
				"id=" + id +
				", title='" + title + '\'' +
				", url1='" + url1 + '\'' +
				", url2='" + url2 + '\'' +
				", url3='" + url3 + '\'' +
				", screen='" + screen + '\'' +
				", category='" + category + '\'' +
				", img1='" + img1 + '\'' +
				", img2='" + img2 + '\'' +
				", updatetime='" + updatetime + '\'' +
				", page=" + page +
				", fromurl='" + fromurl + '\'' +
				", imgNum=" + imgNum +
				", addtime='" + addtime + '\'' +
				'}';
	}

	public void setFromurl(String fromurl) {
        this.fromurl = fromurl == null ? null : fromurl.trim();
    }
}
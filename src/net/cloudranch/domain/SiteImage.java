package net.cloudranch.domain;

public class SiteImage {
	private int id;
	private String image;
	private String createDate;
	private int siteId;
	public SiteImage() {
		super();
	}
	public SiteImage(int id, String image, String createDate, int siteId) {
		super();
		this.id = id;
		this.image = image;
		this.createDate = createDate;
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	@Override
	public String toString() {
		return "SiteImage [id=" + id + ", image=" + image + ", createDate=" + createDate + ", siteId=" + siteId + "]";
	}
}

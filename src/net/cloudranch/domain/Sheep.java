package net.cloudranch.domain;

public class Sheep {
	private String sheepId;
	private String sex;
	private String source;
	private String createDate;
	private int placeId;
	private String fatherId;
	private String motherId;
	private String image;
	private String account;
	private String siteName;
	public Sheep() {
		super();
	}
	public Sheep(String sheepId, String sex, String source, String createDate, int placeId, String fatherId,
			String motherId, String image, String account) {
		super();
		this.sheepId = sheepId;
		this.sex = sex;
		this.source = source;
		this.createDate = createDate;
		this.placeId = placeId;
		this.fatherId = fatherId;
		this.motherId = motherId;
		this.image = image;
		this.account = account;
	}
	public String getSheepId() {
		return sheepId;
	}
	public void setSheepId(String sheepId) {
		this.sheepId = sheepId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String getMotherId() {
		return motherId;
	}
	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Sheep [sheepId=" + sheepId + ", sex=" + sex + ", source=" + source + ", createDate=" + createDate
				+ ", placeId=" + placeId + ", fatherId=" + fatherId + ", motherId=" + motherId + ", image=" + image
				+ ", account=" + account + "]";
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}

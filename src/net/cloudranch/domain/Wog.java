package net.cloudranch.domain;

public class Wog {
	private int id;
	private String remarks;
	private String createDate;
	private String image;
	private String cropId;
	private String cropName;
	public Wog() {
		super();
	}
	public Wog(int id, String remarks, String createDate, String image, String cropId) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.createDate = createDate;
		this.image = image;
		this.cropId = cropId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCropId() {
		return cropId;
	}
	public void setCropId(String cropId) {
		this.cropId = cropId;
	}
	@Override
	public String toString() {
		return "Wog [id=" + id + ", remarks=" + remarks + ", createDate=" + createDate + ", image=" + image
				+ ", cropId=" + cropId + "]";
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	
}

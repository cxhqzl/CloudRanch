package net.cloudranch.domain;

public class Plant {
	private String cropId;
	private String cropName;
	private String createDate;
	private String image;
	private int placeId;
	private String placeName;
	public Plant() {
		super();
	}
	public Plant(String cropId, String cropName, String createDate, String image, int placeId) {
		super();
		this.cropId = cropId;
		this.cropName = cropName;
		this.createDate = createDate;
		this.image = image;
		this.placeId = placeId;
	}
	public String getCropId() {
		return cropId;
	}
	public void setCropId(String cropId) {
		this.cropId = cropId;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
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
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	@Override
	public String toString() {
		return "Plant [cropId=" + cropId + ", cropName=" + cropName + ", createDate=" + createDate + ", image=" + image
				+ ", placeId=" + placeId + "]";
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}

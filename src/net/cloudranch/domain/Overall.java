package net.cloudranch.domain;

public class Overall {
	private int id;
	private String image;
	private String overallURL;
	private int placeId;
	public Overall() {
		super();
	}
	public Overall(int id, String image, String overallURL, int placeId) {
		super();
		this.id = id;
		this.image = image;
		this.overallURL = overallURL;
		this.placeId = placeId;
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
	public String getOverallURL() {
		return overallURL;
	}
	public void setOverallURL(String overallURL) {
		this.overallURL = overallURL;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	@Override
	public String toString() {
		return "Overall [id=" + id + ", image=" + image + ", overallURL=" + overallURL + ", placeId=" + placeId + "]";
	}
}

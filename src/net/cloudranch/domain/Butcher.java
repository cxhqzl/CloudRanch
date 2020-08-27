package net.cloudranch.domain;

public class Butcher {
	private int butcherId;
	private String butcherDate;
	private String image;
	private String province;
	private String city;
	private String county;
	private String location;
	private double lng;
	private double lat;
	private String sheepId;
	private String account;
	
	public Butcher() {
		super();
	}

	public Butcher(int butcherId, String butcherDate, String image, String province, String city, String county,
			String location, double lng, double lat, String sheepId, String account) {
		super();
		this.butcherId = butcherId;
		this.butcherDate = butcherDate;
		this.image = image;
		this.province = province;
		this.city = city;
		this.county = county;
		this.location = location;
		this.lng = lng;
		this.lat = lat;
		this.sheepId = sheepId;
		this.account = account;
	}

	public int getButcherId() {
		return butcherId;
	}

	public void setButcherId(int butcherId) {
		this.butcherId = butcherId;
	}

	public String getButcherDate() {
		return butcherDate;
	}

	public void setButcherDate(String butcherDate) {
		this.butcherDate = butcherDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getSheepId() {
		return sheepId;
	}

	public void setSheepId(String sheepId) {
		this.sheepId = sheepId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Butcher [butcherId=" + butcherId + ", butcherDate=" + butcherDate + ", image=" + image + ", province="
				+ province + ", city=" + city + ", county=" + county + ", location=" + location + ", lng=" + lng
				+ ", lat=" + lat + ", sheepId=" + sheepId + ", account=" + account + "]";
	}
	
}

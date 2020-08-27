package net.cloudranch.domain;

public class SheepTransport {
	private int id;
	private String startDate;
	private String stopDate;
	private String province;
	private String city;
	private String county;
	private String location;
	private double lng;
	private double lat;
	private String sheepId;
	private int peopleId;
	private String account;
	public SheepTransport() {
		super();
	}
	public SheepTransport(int id, String startDate, String stopDate, String province, String city, String county,
			String location, double lng, double lat, String sheepId, int peopleId, String account) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.province = province;
		this.city = city;
		this.county = county;
		this.location = location;
		this.lng = lng;
		this.lat = lat;
		this.sheepId = sheepId;
		this.peopleId = peopleId;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStopDate() {
		return stopDate;
	}
	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
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
	public int getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(int peopleId) {
		this.peopleId = peopleId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "SheepTransport [id=" + id + ", startDate=" + startDate + ", stopDate=" + stopDate + ", province="
				+ province + ", city=" + city + ", county=" + county + ", location=" + location + ", lng=" + lng
				+ ", lat=" + lat + ", sheepId=" + sheepId + ", peopleId=" + peopleId + ", account=" + account + "]";
	}
	
}
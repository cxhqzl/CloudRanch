package net.cloudranch.domain;

public class SiteLocation {
	private int id;
	private int number;
	private String locationName;
	private double lng;
	private double lat;
	private int siteId;
	public SiteLocation() {
		super();
	}
	public SiteLocation(int id, int number, String locationName, double lng, double lat, int siteId) {
		super();
		this.id = id;
		this.number = number;
		this.locationName = locationName;
		this.lng = lng;
		this.lat = lat;
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	@Override
	public String toString() {
		return "SiteLocation [id=" + id + ", number=" + number + ", locationName=" + locationName + ", lng=" + lng
				+ ", lat=" + lat + ", siteId=" + siteId + "]";
	}
}

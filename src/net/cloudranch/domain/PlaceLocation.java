package net.cloudranch.domain;

public class PlaceLocation {
	private int id;
	private int number;
	private String locationName;
	private double lng;
	private double lat;
	private int placeId;
	public PlaceLocation() {
		super();
	}
	public PlaceLocation(int id, int number, String locationName, double lng, double lat, int placeId) {
		super();
		this.id = id;
		this.number = number;
		this.locationName = locationName;
		this.lng = lng;
		this.lat = lat;
		this.placeId = placeId;
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
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	@Override
	public String toString() {
		return "PlaceLocation [id=" + id + ", number=" + number + ", locationName=" + locationName + ", lng=" + lng
				+ ", lat=" + lat + ", placeId=" + placeId + "]";
	}
}

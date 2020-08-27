package net.cloudranch.domain;

public class PlaceSensor {
	private int id;
	private int placeId;
	private int sensorId;
	private String sensorType;
	public PlaceSensor() {
		super();
	}
	public PlaceSensor(int id, int placeId, int sensorId, String sensorType) {
		super();
		this.id = id;
		this.placeId = placeId;
		this.sensorId = sensorId;
		this.sensorType = sensorType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getSensorType() {
		return sensorType;
	}
	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	@Override
	public String toString() {
		return "PlaceSensor [id=" + id + ", placeId=" + placeId + ", sensorId=" + sensorId + ", sensorType="
				+ sensorType + "]";
	}
}

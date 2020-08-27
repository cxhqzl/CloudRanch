package net.cloudranch.domain;

public class Sensor {
	private int sensorId;
	private String type;
	private String sensorName;
	private String mac;
	private int interval;
	private int isReport;
	private String status;
	private double battery;
	private String createDate;
	private int placeId;
	private String picture;
	private String icon;
	public Sensor() {
		super();
	}
	public Sensor(int sensorId, String type, String sensorName, String mac, int interval, int isReport, String status,
			double battery, String createDate, int placeId, String picture, String icon) {
		super();
		this.sensorId = sensorId;
		this.type = type;
		this.sensorName = sensorName;
		this.mac = mac;
		this.interval = interval;
		this.isReport = isReport;
		this.status = status;
		this.battery = battery;
		this.createDate = createDate;
		this.placeId = placeId;
		this.picture = picture;
		this.icon = icon;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getIsReport() {
		return isReport;
	}
	public void setIsReport(int isReport) {
		this.isReport = isReport;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getBattery() {
		return battery;
	}
	public void setBattery(double battery) {
		this.battery = battery;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Sensor [sensorId=" + sensorId + ", type=" + type + ", sensorName=" + sensorName + ", mac=" + mac
				+ ", interval=" + interval + ", isReport=" + isReport + ", status=" + status + ", battery=" + battery
				+ ", createDate=" + createDate + ", placeId=" + placeId + ", picture=" + picture + ", icon=" + icon
				+ "]";
	}
	
}

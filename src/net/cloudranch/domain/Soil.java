package net.cloudranch.domain;

public class Soil {
	private int sensorId;
	private double temp;
	private double hum;
	private String alarmType;
	private String createDate;
	public Soil() {
		super();
	}
	public Soil(int sensorId, double temp, double hum, String alarmType, String createDate) {
		super();
		this.sensorId = sensorId;
		this.temp = temp;
		this.hum = hum;
		this.alarmType = alarmType;
		this.createDate = createDate;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getHum() {
		return hum;
	}
	public void setHum(double hum) {
		this.hum = hum;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Soil [sensorId=" + sensorId + ", temp=" + temp + ", hum=" + hum + ", alarmType=" + alarmType
				+ ", createDate=" + createDate + "]";
	}
}

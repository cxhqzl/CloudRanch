package net.cloudranch.domain;

public class Dew {
	private double temp;
	private String alarmType;
	private String createDate;
	private int sensorId;
	public Dew() {
		super();
	}
	public Dew(double temp, String alarmType, String createDate, int sensorId) {
		super();
		this.temp = temp;
		this.alarmType = alarmType;
		this.createDate = createDate;
		this.sensorId = sensorId;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
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
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	@Override
	public String toString() {
		return "Dew [temp=" + temp + ", alarmType=" + alarmType + ", createDate=" + createDate + ", sensorId="
				+ sensorId + "]";
	}
}

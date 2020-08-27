package net.cloudranch.domain;

public class Air {
	private double temp;
	private double hum;
	private String alarmType;
	private String createDate;
	private int sensorId;
	public Air() {
		super();
	}
	public Air(double temp, double hum, String alarmType, String createDate, int sensorId) {
		super();
		this.temp = temp;
		this.hum = hum;
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
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	@Override
	public String toString() {
		return "Air [temp=" + temp + ", hum=" + hum + ", alarmType=" + alarmType + ", createDate=" + createDate
				+ ", sensorId=" + sensorId + "]";
	}
}

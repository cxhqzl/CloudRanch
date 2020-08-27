package net.cloudranch.domain;

public class Radiation {
	private int sensorId;
	private double intensity;
	private String alarmType;
	private String createDate;
	public Radiation() {
		super();
	}
	public Radiation(int sensorId, double intensity, String alarmType, String createDate) {
		super();
		this.sensorId = sensorId;
		this.intensity = intensity;
		this.alarmType = alarmType;
		this.createDate = createDate;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public double getIntensity() {
		return intensity;
	}
	public void setIntensity(double intensity) {
		this.intensity = intensity;
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
		return "Par [sensorId=" + sensorId + ", intensity=" + intensity + ", alarmType=" + alarmType + ", createDate="
				+ createDate + "]";
	}
}

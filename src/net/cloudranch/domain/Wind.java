package net.cloudranch.domain;

public class Wind {
	private int sensorId;
	private double speed;
	private double direction;
	private String alarmType;
	private String createDate;
	public Wind() {
		super();
	}
	public Wind(int sensorId, double speed, double direction, String alarmType, String createDate) {
		super();
		this.sensorId = sensorId;
		this.speed = speed;
		this.direction = direction;
		this.alarmType = alarmType;
		this.createDate = createDate;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
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
		return "Wind [sensorId=" + sensorId + ", speed=" + speed + ", direction=" + direction + ", alarmType="
				+ alarmType + ", createDate=" + createDate + "]";
	}
}

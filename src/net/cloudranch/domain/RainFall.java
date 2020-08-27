package net.cloudranch.domain;

public class RainFall {
	private double rainFall;
	private String alarmType;
	private String createDate;
	private int sensorId;
	public RainFall() {
		super();
	}
	public RainFall(double rainFall, String alarmType, String createDate, int sensorId) {
		super();
		this.rainFall = rainFall;
		this.alarmType = alarmType;
		this.createDate = createDate;
		this.sensorId = sensorId;
	}
	public double getRainFall() {
		return rainFall;
	}
	public void setRainFall(double rainFall) {
		this.rainFall = rainFall;
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
		return "RainFall [rainFall=" + rainFall + ", alarmType=" + alarmType + ", createDate=" + createDate
				+ ", sensorId=" + sensorId + "]";
	}
}

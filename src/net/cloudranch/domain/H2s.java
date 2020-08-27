package net.cloudranch.domain;

public class H2s {
	private int sensorId;
	private double concen;
	private String alarmType;
	private String createDate;
	public H2s() {
		super();
	}
	public H2s(int sensorId, double concen, String alarmType, String createDate) {
		super();
		this.sensorId = sensorId;
		this.concen = concen;
		this.alarmType = alarmType;
		this.createDate = createDate;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public double getConcen() {
		return concen;
	}
	public void setConcen(double concen) {
		this.concen = concen;
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
		return "H2s [sensorId=" + sensorId + ", concen=" + concen + ", alarmType=" + alarmType + ", createDate="
				+ createDate + "]";
	}
}

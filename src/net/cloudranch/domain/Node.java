package net.cloudranch.domain;

public class Node {
	private int nodeId;
	private String nodeName;
	private String mac;
	private String type;
	private int interval;
	private int isReport;
	private String status;
	private double battery;
	private String createDate;
	private int placeId;
	public Node() {
		super();
	}
	public Node(int nodeId, String nodeName, String mac, String type, int interval, int isReport, String status,
			double battery, String createDate, int placeId) {
		super();
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.mac = mac;
		this.type = type;
		this.interval = interval;
		this.isReport = isReport;
		this.status = status;
		this.battery = battery;
		this.createDate = createDate;
		this.placeId = placeId;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", nodeName=" + nodeName + ", mac=" + mac + ", type=" + type + ", interval="
				+ interval + ", isReport=" + isReport + ", status=" + status + ", battery=" + battery + ", createDate="
				+ createDate + ", placeId=" + placeId + "]";
	}
}

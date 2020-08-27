package net.cloudranch.domain;

public class ButcherInfo {
	private String unitId;
	private String transportId;
	private String company;
	private String account;
	private String butcherDate;
	private String unitName;
	private String remarks;
	private int unitNumber;
	private String sheepId;
	
	public ButcherInfo() {
		super();
	}
	public ButcherInfo(String unitId, String transportId, String company, String account) {
		super();
		this.unitId = unitId;
		this.transportId = transportId;
		this.company = company;
		this.account = account;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "ButcherInfo [unitId=" + unitId + ", unitName=" + unitName + ", account=" + account + "]";
	}
	public String getButcherDate() {
		return butcherDate;
	}
	public void setButcherDate(String butcherDate) {
		this.butcherDate = butcherDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getSheepId() {
		return sheepId;
	}
	public void setSheepId(String sheepId) {
		this.sheepId = sheepId;
	}
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}

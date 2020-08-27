package net.cloudranch.domain;

public class CheckInfo {
	private int checkInfoId;
	private String checkDate;
	private int whether;
	private String remarks;
	private String sheepId;
	private String account;
	
	public CheckInfo() {
		super();
	}
	public CheckInfo(int checkInfoId, String checkDate, int whether, String remarks, String sheepId, String account) {
		super();
		this.checkInfoId = checkInfoId;
		this.checkDate = checkDate;
		this.whether = whether;
		this.remarks = remarks;
		this.sheepId = sheepId;
		this.account = account;
	}
	public int getCheckInfoId() {
		return checkInfoId;
	}
	public void setCheckInfoId(int checkInfoId) {
		this.checkInfoId = checkInfoId;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public int getWhether() {
		return whether;
	}
	public void setWhether(int whether) {
		this.whether = whether;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSheepId() {
		return sheepId;
	}
	public void setSheepId(String sheepId) {
		this.sheepId = sheepId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "checkinfo [checkInfoId=" + checkInfoId + ", checkDate=" + checkDate + ", whether=" + whether
				+ ", remarks=" + remarks + ", sheepId=" + sheepId + ", account=" + account + "]";
	}
}

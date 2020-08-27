package net.cloudranch.domain;

public class LoginLog {
	private String account;
	private String address;
	private String ip;
	private String loginDate;
	private int type;
	public LoginLog() {
		super();
	}
	public LoginLog(String account, String address, String ip, String loginDate, int type) {
		super();
		this.account = account;
		this.address = address;
		this.ip = ip;
		this.loginDate = loginDate;
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "LoginLog [account=" + account + ", address=" + address + ", ip=" + ip + ", loginDate=" + loginDate
				+ ", type=" + type + "]";
	}
}

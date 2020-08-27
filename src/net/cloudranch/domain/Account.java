package net.cloudranch.domain;

public class Account {
	private String account;
	private String password;
	private int role;
	public Account() {
		super();
	}
	public Account(String account, String password, int role) {
		super();
		this.account = account;
		this.password = password;
		this.role = role;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Account [account=" + account + ", password=" + password + ", role=" + role + "]";
	}
}

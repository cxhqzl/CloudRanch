package net.cloudranch.domain;

public class Forage {
	private int forageId;
	private String forageName;
	private String produceDate;
	private String account;
	public Forage() {
		super();
	}
	public Forage(int forageId, String forageName, String produceDate, String account) {
		super();
		this.forageId = forageId;
		this.forageName = forageName;
		this.produceDate = produceDate;
		this.account = account;
	}
	public int getForageId() {
		return forageId;
	}
	public void setForageId(int forageId) {
		this.forageId = forageId;
	}
	public String getForageName() {
		return forageName;
	}
	public void setForageName(String forageName) {
		this.forageName = forageName;
	}
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Forage [forageId=" + forageId + ", forageName=" + forageName + ", produceDate=" + produceDate
				+ ", account=" + account + "]";
	}
}

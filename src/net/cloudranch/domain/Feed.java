package net.cloudranch.domain;

public class Feed {
	private int feedId;
	private double feedNumber;
	private String feedDate;
	private int forageId;
	private String sheepId;
	private String account;
	private String forageName;
	public Feed() {
		super();
	}
	public Feed(int feedId, double feedNumber, String feedDate, int forageId, String sheepId, String account) {
		super();
		this.feedId = feedId;
		this.feedNumber = feedNumber;
		this.feedDate = feedDate;
		this.forageId = forageId;
		this.sheepId = sheepId;
		this.account = account;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public double getFeedNumber() {
		return feedNumber;
	}
	public void setFeedNumber(double feedNumber) {
		this.feedNumber = feedNumber;
	}
	public String getFeedDate() {
		return feedDate;
	}
	public void setFeedDate(String feedDate) {
		this.feedDate = feedDate;
	}
	public int getForageId() {
		return forageId;
	}
	public void setForageId(int forageId) {
		this.forageId = forageId;
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
		return "Feed [feedId=" + feedId + ", feedNumber=" + feedNumber + ", feedDate=" + feedDate + ", forageId="
				+ forageId + ", sheepId=" + sheepId + ", account=" + account + "]";
	}
	public String getForageName() {
		return forageName;
	}
	public void setForageName(String forageName) {
		this.forageName = forageName;
	}
	
}

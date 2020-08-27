package net.cloudranch.domain;

public class DividerPlace {
	private int dividerId;
	private String corp;
	private double square;
	private int placeId;
	private String account;
	public DividerPlace() {
		super();
	}
	public DividerPlace(int dividerId, String corp, double square, int placeId, String account) {
		super();
		this.dividerId = dividerId;
		this.corp = corp;
		this.square = square;
		this.placeId = placeId;
		this.account = account;
	}
	public int getDividerId() {
		return dividerId;
	}
	public void setDividerId(int dividerId) {
		this.dividerId = dividerId;
	}
	public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		this.corp = corp;
	}
	public double getSquare() {
		return square;
	}
	public void setSquare(double square) {
		this.square = square;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "DividerPlace [dividerId=" + dividerId + ", corp=" + corp + ", square=" + square + ", placeId=" + placeId
				+ ", account=" + account + "]";
	}
}

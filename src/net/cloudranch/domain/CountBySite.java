package net.cloudranch.domain;

public class CountBySite {
	private int placeCount;
	private int placeCountAni;
	private int userCount;
	private int cropCount;
	private int vidoCount;
	private int sensorCount;
	private int placeNotCount;
	private int siteId;
	
	public CountBySite() {
		super();
	}

	public CountBySite(int placeCount, int userCount, int cropCount, int vidoCount, int sensorCount, int placeNotCount,
			int siteId) {
		super();
		this.placeCount = placeCount;
		this.userCount = userCount;
		this.cropCount = cropCount;
		this.vidoCount = vidoCount;
		this.sensorCount = sensorCount;
		this.placeNotCount = placeNotCount;
		this.siteId = siteId;
	}

	public int getPlaceCount() {
		return placeCount;
	}

	public void setPlaceCount(int placeCount) {
		this.placeCount = placeCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getCropCount() {
		return cropCount;
	}

	public void setCropCount(int cropCount) {
		this.cropCount = cropCount;
	}

	public int getVidoCount() {
		return vidoCount;
	}

	public void setVidoCount(int vidoCount) {
		this.vidoCount = vidoCount;
	}

	public int getSensorCount() {
		return sensorCount;
	}

	public void setSensorCount(int sensorCount) {
		this.sensorCount = sensorCount;
	}

	public int getPlaceNotCount() {
		return placeNotCount;
	}

	public void setPlaceNotCount(int placeNotCount) {
		this.placeNotCount = placeNotCount;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public String toString() {
		return "CountBySite [placeCount=" + placeCount + ", userCount=" + userCount + ", cropCount=" + cropCount
				+ ", vidoCount=" + vidoCount + ", sensorCount=" + sensorCount + ", placeNotCount=" + placeNotCount
				+ ", siteId=" + siteId + "]";
	}

	public int getPlaceCountAni() {
		return placeCountAni;
	}

	public void setPlaceCountAni(int placeCountAni) {
		this.placeCountAni = placeCountAni;
	}
}

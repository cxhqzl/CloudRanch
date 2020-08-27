package net.cloudranch.domain;

public class Site {
	private int siteId;
	private String siteName;
	private double square;
	private String province;
	private String city;
	private String county;
	private String location;
	private double lng;
	private double lat;
	private String createDate;
	private String account;
	private String remarks;
	/**¸½¼Ó×Ö¶Î*/
	private String image;
	private String uaerName;
	private String crops;
	public Site() {
		super();
	}
	public Site(int siteId, String siteName, double square, String province, String city, String county,
			String location, double lng, double lat, String createDate, String account, String remarks) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.square = square;
		this.province = province;
		this.city = city;
		this.county = county;
		this.location = location;
		this.lng = lng;
		this.lat = lat;
		this.createDate = createDate;
		this.account = account;
		this.remarks = remarks;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public double getSquare() {
		return square;
	}
	public void setSquare(double square) {
		this.square = square;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUaerName() {
		return uaerName;
	}
	public void setUaerName(String uaerName) {
		this.uaerName = uaerName;
	}
	public String getCrops() {
		return crops;
	}
	public void setCrops(String crops) {
		this.crops = crops;
	}
	@Override
	public String toString() {
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", square=" + square + ", province=" + province
				+ ", city=" + city + ", county=" + county + ", location=" + location + ", lng=" + lng + ", lat=" + lat
				+ ", createDate=" + createDate + ", account=" + account + ", remarks=" + remarks + ", image=" + image
				+ ", uaerName=" + uaerName + ", crops=" + crops + "]";
	}
	
}

package net.cloudranch.domain;

public class TransportCompany {
	
	private int companyId;
	private String name;
	private String province;
	private String city;
	private String county;
	private String location;
	private double lng;
	private double lat;
	private String account;
	public TransportCompany() {
		super();
	}
	
	public TransportCompany(int companyId, String name, String province, String city, String county, String location,
			double lng, double lat, String account) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.province = province;
		this.city = city;
		this.county = county;
		this.location = location;
		this.lng = lng;
		this.lat = lat;
		this.account = account;
	}

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "TransportCompany [companyId=" + companyId + ", name=" + name + ", province=" + province + ", city="
				+ city + ", county=" + county + ", lng=" + lng + ", lat=" + lat + ", account=" + account + "]";
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}

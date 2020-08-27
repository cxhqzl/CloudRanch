package net.cloudranch.domain;

public class TransportPeople {
	private int peopleId;
	private String name;
	private String phone;
	private String carId;
	private String province;
	private String city;
	private String county;
	private String location;
	private double lng;
	private double lat;
	private int companyId;
	private String account;
	public TransportPeople() {
		super();
	}
	public TransportPeople(int peopleId, String name, String phone, String carId, String province, String city,
			String county, String location, double lng, double lat, int companyId, String account) {
		super();
		this.peopleId = peopleId;
		this.name = name;
		this.phone = phone;
		this.carId = carId;
		this.province = province;
		this.city = city;
		this.county = county;
		this.location = location;
		this.lng = lng;
		this.lat = lat;
		this.companyId = companyId;
		this.account = account;
	}
	public int getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(int peopleId) {
		this.peopleId = peopleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
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
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "TransportPeople [peopleId=" + peopleId + ", name=" + name + ", phone=" + phone + ", carId=" + carId
				+ ", province=" + province + ", city=" + city + ", county=" + county + ", location=" + location
				+ ", lng=" + lng + ", lat=" + lat + ", companyId=" + companyId + ", account=" + account + "]";
	}
	
}

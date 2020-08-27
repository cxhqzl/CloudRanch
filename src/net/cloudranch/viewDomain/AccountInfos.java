package net.cloudranch.viewDomain;

public class AccountInfos {
	private String account;
	private String userName;
	private String image;
	private String sex;
	private int age;
	private String province;
	private String city;
	private String county;
	private String location;
	private double lng;
	private double lat;
	private String phone;
	private String createDate;
	private int role;
	public AccountInfos() {
		super();
	}
	public AccountInfos(String account, String userName, String image, String sex, int age, String province,
			String city, String county, String location, double lng, double lat, String phone, String createDate,
			int role) {
		super();
		this.account = account;
		this.userName = userName;
		this.image = image;
		this.sex = sex;
		this.age = age;
		this.province = province;
		this.city = city;
		this.county = county;
		this.location = location;
		this.lng = lng;
		this.lat = lat;
		this.phone = phone;
		this.createDate = createDate;
		this.role = role;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AccountInfos [account=" + account + ", userName=" + userName + ", image=" + image + ", sex=" + sex
				+ ", age=" + age + ", province=" + province + ", city=" + city + ", county=" + county + ", location="
				+ location + ", lng=" + lng + ", lat=" + lat + ", phone=" + phone + ", createDate=" + createDate
				+ ", role=" + role + "]";
	}
}

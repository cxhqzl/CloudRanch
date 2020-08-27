package net.cloudranch.domain;

public class AccountInfo {
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
	private String email;
	private String createDate;
	private int role;
	public AccountInfo() {
		super();
	}
	
	public AccountInfo(String account, String userName, String image, String sex, int age, String province, String city,
			String county, String location, double lng, double lat, String phone, String email, String createDate) {
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
		this.email = email;
		this.createDate = createDate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "AccountInfo [account=" + account + ", userName=" + userName + ", image=" + image + ", sex=" + sex
				+ ", age=" + age + ", province=" + province + ", city=" + city + ", county=" + county + ", location="
				+ location + ", lng=" + lng + ", lat=" + lat + ", phone=" + phone + ", email=" + email + ", createDate="
				+ createDate + ", role=" + role + "]";
	}
	
}

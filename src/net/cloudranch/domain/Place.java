package net.cloudranch.domain;

public class Place {
	private int placeId;
	private String placeName;
	private double square;
	private String createDate;
	private int siteId;
	private String vidoType;
	private String vidoUrl;
	private String crop;
	private String type;
	private String account;
	private String strokeColor;
	private String fillColor;
	private double strokeOpacity;
	private double fillOpacity;
	/**附加属性*/
	private String userName;
	private int count;
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
	/** end 附加属性*/
	public Place() {
		super();
	}
	
	public Place(int placeId, String placeName, double square, String createDate, int siteId, String vidoType,
			String vidoUrl, String crop, String type, String account, String strokeColor, String fillColor,
			double strokeOpacity, double fillOpacity) {
		super();
		this.placeId = placeId;
		this.placeName = placeName;
		this.square = square;
		this.createDate = createDate;
		this.siteId = siteId;
		this.vidoType = vidoType;
		this.vidoUrl = vidoUrl;
		this.crop = crop;
		this.type = type;
		this.account = account;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.strokeOpacity = strokeOpacity;
		this.fillOpacity = fillOpacity;
	}


	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeName=" + placeName + ", square=" + square + ", createDate="
				+ createDate + ", siteId=" + siteId + ", vidoType=" + vidoType + ", vidoUrl=" + vidoUrl + ", crop="
				+ crop + ", type=" + type + ", account=" + account + ", strokeColor=" + strokeColor + ", fillColor="
				+ fillColor + ", strokeOpacity=" + strokeOpacity + ", fillOpacity=" + fillOpacity + "]";
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public double getSquare() {
		return square;
	}

	public void setSquare(double square) {
		this.square = square;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getVidoType() {
		return vidoType;
	}

	public void setVidoType(String vidoType) {
		this.vidoType = vidoType;
	}

	public String getVidoUrl() {
		return vidoUrl;
	}

	public void setVidoUrl(String vidoUrl) {
		this.vidoUrl = vidoUrl;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public double getStrokeOpacity() {
		return strokeOpacity;
	}

	public void setStrokeOpacity(double strokeOpacity) {
		this.strokeOpacity = strokeOpacity;
	}

	public double getFillOpacity() {
		return fillOpacity;
	}

	public void setFillOpacity(double fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
}

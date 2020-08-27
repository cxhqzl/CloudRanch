package net.cloudranch.domain;

public class GrowInfo {
	private int growInfoId;
	private String createDate;
	private double weight;
	private String image;
	private int age;
	private String sheepId;
	private String account;
	public GrowInfo() {
		super();
	}
	public GrowInfo(int growInfoId, String createDate, double weight, String image, int age, String sheepid,
			String account) {
		super();
		this.growInfoId = growInfoId;
		this.createDate = createDate;
		this.weight = weight;
		this.image = image;
		this.age = age;
		this.sheepId = sheepid;
		this.account = account;
	}
	public int getGrowInfoId() {
		return growInfoId;
	}
	public void setGrowInfoId(int growInfoId) {
		this.growInfoId = growInfoId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSheepid() {
		return sheepId;
	}
	public void setSheepid(String sheepid) {
		this.sheepId = sheepid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "GrowInfo [growInfoId=" + growInfoId + ", createDate=" + createDate + ", weight=" + weight + ", image="
				+ image + ", age=" + age + ", sheepid=" + sheepId + ", account=" + account + "]";
	}
}

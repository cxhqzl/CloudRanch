package net.cloudranch.viewDomain;

public class Crops {
	private String crops;
	private int siteId;
	
	public Crops() {
		super();
	}

	public Crops(String crops, int siteId) {
		super();
		this.crops = crops;
		this.siteId = siteId;
	}

	public String getCrops() {
		return crops;
	}

	public void setCrops(String crops) {
		this.crops = crops;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public String toString() {
		return "Crops [crops=" + crops + ", siteId=" + siteId + "]";
	}
}

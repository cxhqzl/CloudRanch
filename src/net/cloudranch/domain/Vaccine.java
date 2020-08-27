package net.cloudranch.domain;

public class Vaccine {
	private int vaccineId;
	private String vaccineName;
	private String produceDate;
	private String vaccineBrand;
	private String account;
	public Vaccine() {
		super();
	}
	public Vaccine(int vaccineId, String vaccineName, String produceDate, String vaccineBrand, String account) {
		super();
		this.vaccineId = vaccineId;
		this.vaccineName = vaccineName;
		this.produceDate = produceDate;
		this.vaccineBrand = vaccineBrand;
		this.account = account;
	}
	public int getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public String getVaccineBrand() {
		return vaccineBrand;
	}
	public void setVaccineBrand(String vaccineBrand) {
		this.vaccineBrand = vaccineBrand;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Vaccine [vaccineId=" + vaccineId + ", vaccineName=" + vaccineName + ", produceDate=" + produceDate
				+ ", vaccineBrand=" + vaccineBrand + ", account=" + account + "]";
	}
	
}

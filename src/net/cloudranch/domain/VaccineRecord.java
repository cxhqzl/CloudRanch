package net.cloudranch.domain;

public class VaccineRecord {
	private int vaccineRecordId;
	private String recordDate;
	private int vaccineId;
	private String sheepId;
	private String account;
	private String vaccineName;
	private String vaccineBrand;
	public VaccineRecord() {
		super();
	}
	public VaccineRecord(int vaccineRecordId, String recordDate, int vaccineId, String sheepId, String account) {
		super();
		this.vaccineRecordId = vaccineRecordId;
		this.recordDate = recordDate;
		this.vaccineId = vaccineId;
		this.sheepId = sheepId;
		this.account = account;
	}
	public int getVaccineRecordId() {
		return vaccineRecordId;
	}
	public void setVaccineRecordId(int vaccineRecordId) {
		this.vaccineRecordId = vaccineRecordId;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public int getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}
	public String getSheepId() {
		return sheepId;
	}
	public void setSheepId(String sheepId) {
		this.sheepId = sheepId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "VaccineRecord [vaccineRecordId=" + vaccineRecordId + ", recordDate=" + recordDate + ", vaccineId="
				+ vaccineId + ", sheepId=" + sheepId + ", account=" + account + "]";
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getVaccineBrand() {
		return vaccineBrand;
	}
	public void setVaccineBrand(String vaccineBrand) {
		this.vaccineBrand = vaccineBrand;
	}
}

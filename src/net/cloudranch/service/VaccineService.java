package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Vaccine;

public interface VaccineService {
	/**�������*/
	public boolean add(Vaccine vaccine);
	
	/**ɾ������*/
	public boolean del(int vaccineId);
	
	/**�޸�����*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ����*/
	public List<Vaccine> query(Map<String,Object> map);
	
	public Vaccine getVaccineName(Map<String,Object> map);
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Vaccine;

public interface VaccineService {
	/**添加数据*/
	public boolean add(Vaccine vaccine);
	
	/**删除数据*/
	public boolean del(int vaccineId);
	
	/**修改数据*/
	public boolean modified(Map<String,Object> map);
	
	/**查询数据*/
	public List<Vaccine> query(Map<String,Object> map);
	
	public Vaccine getVaccineName(Map<String,Object> map);
}

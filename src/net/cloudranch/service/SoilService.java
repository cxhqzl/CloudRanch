package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Soil;

public interface SoilService {
	
	/**���*/
	public boolean add(Soil soil);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Soil> query(Map<String,Object> map);
	
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Radiation;

public interface RadiationService {
	
	/**���*/
	public boolean add(Radiation radiation);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Radiation> query(Map<String,Object> map);
	
}

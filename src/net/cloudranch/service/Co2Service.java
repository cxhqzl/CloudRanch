package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Co2;

public interface Co2Service {
	
	/**���*/
	public boolean add(Co2 co2);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Co2> query(Map<String,Object> map);
	
}

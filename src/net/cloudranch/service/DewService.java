package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Dew;

public interface DewService {
	
	/**���*/
	public boolean add(Dew dew);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Dew> query(Map<String,Object> map);
	
}

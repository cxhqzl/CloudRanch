package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Par;

public interface ParService {
	
	/**���*/
	public boolean add(Par par);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Par> query(Map<String,Object> map);
	
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Wind;

public interface WindService {
	
	/**���*/
	public boolean add(Wind wind);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Wind> query(Map<String,Object> map);
	
}

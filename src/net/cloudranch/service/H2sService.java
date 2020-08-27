package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.H2s;

public interface H2sService {
	
	/**���*/
	public boolean add(H2s h2s);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<H2s> query(Map<String,Object> map);
	
}

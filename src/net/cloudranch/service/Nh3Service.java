package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Nh3;

public interface Nh3Service {
	
	/**���*/
	public boolean add(Nh3 nh3);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Nh3> query(Map<String,Object> map);
	
}

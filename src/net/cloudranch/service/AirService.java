package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Air;

public interface AirService {
	
	/**���*/
	public boolean add(Air air);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Air> query(Map<String,Object> map);
	
}

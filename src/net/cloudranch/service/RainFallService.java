package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.RainFall;

public interface RainFallService {
	
	/**���*/
	public boolean add(RainFall rainFall);
	
	/**ɾ��*/
	public boolean delete(Map<String,Object> map);
	
	/**��ѯ*/
	public List<RainFall> query(Map<String,Object> map);
	
}

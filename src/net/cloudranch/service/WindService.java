package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Wind;

public interface WindService {
	
	/**Ìí¼Ó*/
	public boolean add(Wind wind);
	
	/**É¾³ı*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Wind> query(Map<String,Object> map);
	
}

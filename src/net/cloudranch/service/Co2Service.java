package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Co2;

public interface Co2Service {
	
	/**Ìí¼Ó*/
	public boolean add(Co2 co2);
	
	/**É¾³ý*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Co2> query(Map<String,Object> map);
	
}

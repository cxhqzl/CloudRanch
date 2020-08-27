package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Air;

public interface AirService {
	
	/**Ìí¼Ó*/
	public boolean add(Air air);
	
	/**É¾³ı*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Air> query(Map<String,Object> map);
	
}

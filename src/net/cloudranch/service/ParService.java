package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Par;

public interface ParService {
	
	/**Ìí¼Ó*/
	public boolean add(Par par);
	
	/**É¾³ı*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Par> query(Map<String,Object> map);
	
}

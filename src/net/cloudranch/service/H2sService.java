package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.H2s;

public interface H2sService {
	
	/**Ìí¼Ó*/
	public boolean add(H2s h2s);
	
	/**É¾³ı*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<H2s> query(Map<String,Object> map);
	
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Nh3;

public interface Nh3Service {
	
	/**Ìí¼Ó*/
	public boolean add(Nh3 nh3);
	
	/**É¾³ı*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Nh3> query(Map<String,Object> map);
	
}

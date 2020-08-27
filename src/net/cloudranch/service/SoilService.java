package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Soil;

public interface SoilService {
	
	/**Ìí¼Ó*/
	public boolean add(Soil soil);
	
	/**É¾³ý*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Soil> query(Map<String,Object> map);
	
}

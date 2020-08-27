package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Radiation;

public interface RadiationService {
	
	/**Ìí¼Ó*/
	public boolean add(Radiation radiation);
	
	/**É¾³ý*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Radiation> query(Map<String,Object> map);
	
}

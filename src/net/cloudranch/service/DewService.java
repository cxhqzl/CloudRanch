package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Dew;

public interface DewService {
	
	/**Ìí¼Ó*/
	public boolean add(Dew dew);
	
	/**É¾³ý*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Dew> query(Map<String,Object> map);
	
}

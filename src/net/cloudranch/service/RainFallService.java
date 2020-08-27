package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.RainFall;

public interface RainFallService {
	
	/**Ìí¼Ó*/
	public boolean add(RainFall rainFall);
	
	/**É¾³ý*/
	public boolean delete(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<RainFall> query(Map<String,Object> map);
	
}

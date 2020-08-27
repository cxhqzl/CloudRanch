package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Grow;

public interface GrowService {
	
	/**Ìí¼Ó*/
	public boolean addGrow(Grow grow);
	
	/**É¾³ý*/
	public boolean delGrow(int id);
	
	/**ÐÞ¸Ä*/
	public boolean modifiedGrow(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Grow> queryGrows(Map<String,Object> map);
	
}

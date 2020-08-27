package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Wog;

public interface WogService {
	
	/**Ìí¼Ó*/
	public boolean addWog(Wog wog);
	
	/**É¾³ý*/
	public boolean delWog(int id);
	
	/**ÐÞ¸Ä*/
	public boolean modifiedWog(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Wog> queryWogs(Map<String,Object> map);
	
}

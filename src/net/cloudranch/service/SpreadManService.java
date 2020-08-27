package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.SpreadMan;

public interface SpreadManService {
	
	/**Ìí¼Ó*/
	public boolean addSpreadMan(SpreadMan spreadMan);
	
	/**É¾³ı*/
	public boolean delSpreadMan(int id);
	
	/**ĞŞ¸Ä*/
	public boolean modifiedSpreadMan(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<SpreadMan> querySpreadMans(Map<String,Object> map);
	
}

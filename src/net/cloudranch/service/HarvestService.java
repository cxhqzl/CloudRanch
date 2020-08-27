package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Harvest;

public interface HarvestService {
	
	/**Ìí¼Ó*/
	public boolean addHarvest(Harvest harvest);
	
	/**É¾³ı*/
	public boolean delHarvest(int id);
	
	/**ĞŞ¸Ä*/
	public boolean modifiedHarvest(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Harvest> queryHarvests(Map<String,Object> map);
	
}

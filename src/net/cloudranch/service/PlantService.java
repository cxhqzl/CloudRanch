package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Plant;

public interface PlantService {

	/**Ìí¼Ó*/
	public boolean addPlant(Plant plant);
	
	/**É¾³ý*/
	public boolean delPlant(String cropId);
	
	/**ÐÞ¸Ä*/
	public boolean modifiPlant(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Plant> queryPlant(Map<String,Object> map);
}

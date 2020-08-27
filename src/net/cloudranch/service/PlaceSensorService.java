package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.PlaceSensor;

public interface PlaceSensorService {
	
	/**Ìí¼Ó*/
	public boolean addPlaceSensor(PlaceSensor placeSensor);
	
	/**É¾³ý*/
	public boolean del(int id);
	
	/**ÐÞ¸Ä*/
	public boolean modifiedPlaceSensor(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<PlaceSensor> queryPlaceSensors(Map<String,Object> map);
	
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.PlaceSensor;

public interface PlaceSensorService {
	
	/**���*/
	public boolean addPlaceSensor(PlaceSensor placeSensor);
	
	/**ɾ��*/
	public boolean del(int id);
	
	/**�޸�*/
	public boolean modifiedPlaceSensor(Map<String,Object> map);
	
	/**��ѯ*/
	public List<PlaceSensor> queryPlaceSensors(Map<String,Object> map);
	
}

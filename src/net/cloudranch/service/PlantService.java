package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Plant;

public interface PlantService {

	/**���*/
	public boolean addPlant(Plant plant);
	
	/**ɾ��*/
	public boolean delPlant(String cropId);
	
	/**�޸�*/
	public boolean modifiPlant(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Plant> queryPlant(Map<String,Object> map);
}

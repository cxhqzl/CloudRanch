package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Harvest;

public interface HarvestService {
	
	/**���*/
	public boolean addHarvest(Harvest harvest);
	
	/**ɾ��*/
	public boolean delHarvest(int id);
	
	/**�޸�*/
	public boolean modifiedHarvest(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Harvest> queryHarvests(Map<String,Object> map);
	
}

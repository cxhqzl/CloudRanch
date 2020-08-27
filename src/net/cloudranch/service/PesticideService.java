package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Pesticide;

public interface PesticideService {
	
	/**���*/
	public boolean addPesticide(Pesticide pesticide);
	
	/**ɾ��*/
	public boolean delPesticide(int id);
	
	/**�޸�*/
	public boolean modifiedPesticide(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Pesticide> queryPesticides(Map<String,Object> map);
	
}

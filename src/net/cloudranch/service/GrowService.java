package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Grow;

public interface GrowService {
	
	/**���*/
	public boolean addGrow(Grow grow);
	
	/**ɾ��*/
	public boolean delGrow(int id);
	
	/**�޸�*/
	public boolean modifiedGrow(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Grow> queryGrows(Map<String,Object> map);
	
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.SpreadMan;

public interface SpreadManService {
	
	/**���*/
	public boolean addSpreadMan(SpreadMan spreadMan);
	
	/**ɾ��*/
	public boolean delSpreadMan(int id);
	
	/**�޸�*/
	public boolean modifiedSpreadMan(Map<String,Object> map);
	
	/**��ѯ*/
	public List<SpreadMan> querySpreadMans(Map<String,Object> map);
	
}

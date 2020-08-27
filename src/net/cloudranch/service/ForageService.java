package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Forage;

public interface ForageService {
	/**��������*/
	public boolean add(Forage forage);
	
	/**ɾ������*/
	public boolean del(int forageId);
	
	/**�޸�����*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ����*/
	public List<Forage> query(Map<String,Object> map);
	
	/**��ȡ������*/
	public String getForageName(Map<String,Object> map);
	
}

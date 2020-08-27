package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Sheep;

public interface SheepService {
	
	/**���*/
	public boolean add(Sheep sheep);
	
	/**ɾ��*/
	public boolean del(String sheepId);
	
	/**�޸�*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ*/
	public Sheep query(Map<String,Object> map);
	
	/**��ѯ������¼�������*/
	public Map<String, Object> querySheepId(Map<String,Object> map);
	
	/**��ѯ��ID�Ƿ���¼��*/
	public boolean weatherSheepId(Map<String,Object> map);
	
	/**��ѯ������������������Ϣ*/
	public List<Sheep> queryByPlaceIdAndAccount(Map<String,Object> map);
	
	/**��ѯ��¼����*/
	public int queryCount(Map<String,Object> map);
	
	
	/**��ȡ��γ��*/
	public Map<String,Double> getLngAndLat(Map<String,Object> map);
	
	
	/**��ѯid�Ƿ����*/
	public boolean querySheepIdExist(Map<String,Object> map);
	
}

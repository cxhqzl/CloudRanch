package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Butcher;

public interface ButcherService {
	/**��Ӽ�¼*/
	public boolean add(Butcher butcher);
	
	/**ɾ����¼*/
	public boolean del(int butcherId);
	
	/**�޸�*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ*/
	public Butcher query(Map<String,Object> map);
	
	/**��ѯ���ںͱ�ע*/
	public List<Butcher> queryDateAndRemarks(Map<String,Object> map);
	
	/**��ѯ������Ϣ*/
	public List<Butcher> queryAll(Map<String,Object> map);
	
}

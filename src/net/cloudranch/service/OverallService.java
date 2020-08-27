package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Overall;

public interface OverallService {
	
	/**���ȫ��ͼ��Ϣ*/
	public boolean addOverall(Overall overall);
	
	/**ɾ��ȫ��ͼ��Ϣ*/
	public boolean delOverall(int id);
	
	/**�޸�ȫ��ͼ��Ϣ*/
	public boolean modifiedOverall(Map<String,Object> map);
	
	/**��ѯȫ��ͼ��Ϣ*/
	public List<Overall> queryOveralls(Map<String,Object> map);
	
}

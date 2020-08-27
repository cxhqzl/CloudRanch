package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.ButcherInfo;

public interface ButcherInfoService {
	/**��Ӽ�¼*/
	public boolean add(ButcherInfo butcherInfo);
	
	/**ɾ����¼*/
	public boolean del(String unitId);
	
	/**�޸�*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ*/
	public ButcherInfo query(Map<String,Object> map);
	
	/**��ѯ��ע��ID*/
	public Map<String,Object> queryUnitIdByAccount(Map<String,Object> map);
	
	/**��ѯ����������������Ϣ*/
	public List<ButcherInfo> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**��ѯ��¼����*/
	public int queryCount(Map<String,Object> map);
	
	/**��ѯID�Ƿ����*/
	public boolean queryUnitIdExist(Map<String,Object> map);
}

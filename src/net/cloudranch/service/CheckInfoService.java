package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.CheckInfo;

public interface CheckInfoService {
	/**��Ӽ�¼*/
	public boolean add(CheckInfo checkInfo);
	
	/**ɾ����¼*/
	public boolean del(int checkInfoId);
	
	/**�޸�*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ*/
	public CheckInfo query(Map<String,Object> map);
	
	/**��ѯ�������������м�¼*/
	public List<CheckInfo> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**��ѯ��¼����*/
	public int queryCount(Map<String,Object> map);
}

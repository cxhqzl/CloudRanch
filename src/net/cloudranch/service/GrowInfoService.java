package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.GrowInfo;

public interface GrowInfoService {
	/**���*/
	public boolean add(GrowInfo growInfo);
	
	/**ɾ��*/
	public boolean del(int growInfoId);
	
	/**�޸�*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ*/
	public GrowInfo query(Map<String,Object> map);
	
	/**��ѯ���һ�������Ƭ*/
	public String queryImageBySheepId(Map<String,Object> map);
	
	/**��ѯ�������������м�¼*/
	public List<GrowInfo> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**��ѯ��¼����*/
	public int queryCount(Map<String,Object> map);
	
	/**��ȡ������Ϣ���һ��ͼƬ*/
	public String getLastImage(Map<String,Object> map);
}

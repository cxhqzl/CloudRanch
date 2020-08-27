package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Site;

public interface SiteService {
	/**���վ��*/
	public boolean addSite(Site site);
	
	/**ɾ��վ��*/
	public boolean delSite(int siteId);
	
	/**�޸�վ��*/
	public boolean modifiSite(Map<String,Object> map);
	
	/**�޸�վ��������*/
	public boolean modifiySiteToAccount(Map<String,Object> map);
	
	/**��ѯվ����Ϣ*/
	public Site querySite(Map<String,Object> map);
	
	/**�ж�վ���Ƿ�����*/
	public boolean siteClaim(Map<String,Object> map);
	
	/**��ȡһ�����վ��*/
	public Site getOneSite(Map<String,Object> map);
	
	/**����վ��*/
	public List<Site> searchSites(Map<String,Object> map);
	
	/**����Ա��ȡվ��*/
	public List<Site> adminGetSites(Map<String,Object> map);
}

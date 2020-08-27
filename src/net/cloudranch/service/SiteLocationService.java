package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.SiteLocation;

public interface SiteLocationService {
	/**���վ��λ�ü�¼*/
	public boolean addSiteLocation(SiteLocation siteLocation);
	
	/**ɾ��վ��λ��*/
	public boolean delSiteLocation(int id);
	
	/**�޸�վ��λ��*/
	public boolean modifiySiteLocation(Map<String,Object> map);
	
	/**��ѯվ��ͼƬ��Ϣ*/
	public List<SiteLocation> querySiteLocations(Map<String,Object> map);
	
	/**��ѯ����Ƿ��Ѵ���*/
	public boolean numberExists(Map<String,Object> map);
}

package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.provider.ViewProvider;
import net.cloudranch.viewDomain.Crops;
import net.cloudranch.viewDomain.SiteInfos;

public interface QueryInfosForView {
	
	/**��ѯվ����Ϣ��ͼ*/
	@SelectProvider(type=ViewProvider.class,method="selectSiteInfos")
	public List<SiteInfos> selectSiteInfos(Map<String,Object> map);
	
	/**��ѯ����������ͼ*/
	@SelectProvider(type=ViewProvider.class,method="selectCrops")
	public List<Crops> selectCrops(Map<String,Object> map);
	
}

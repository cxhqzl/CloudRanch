package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Site;

public interface SiteService {
	/**添加站点*/
	public boolean addSite(Site site);
	
	/**删除站点*/
	public boolean delSite(int siteId);
	
	/**修改站点*/
	public boolean modifiSite(Map<String,Object> map);
	
	/**修改站点所属人*/
	public boolean modifiySiteToAccount(Map<String,Object> map);
	
	/**查询站点信息*/
	public Site querySite(Map<String,Object> map);
	
	/**判断站点是否被认领*/
	public boolean siteClaim(Map<String,Object> map);
	
	/**获取一个随机站点*/
	public Site getOneSite(Map<String,Object> map);
	
	/**搜索站点*/
	public List<Site> searchSites(Map<String,Object> map);
	
	/**管理员获取站点*/
	public List<Site> adminGetSites(Map<String,Object> map);
}

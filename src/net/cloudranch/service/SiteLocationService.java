package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.SiteLocation;

public interface SiteLocationService {
	/**添加站点位置记录*/
	public boolean addSiteLocation(SiteLocation siteLocation);
	
	/**删除站点位置*/
	public boolean delSiteLocation(int id);
	
	/**修改站点位置*/
	public boolean modifiySiteLocation(Map<String,Object> map);
	
	/**查询站点图片信息*/
	public List<SiteLocation> querySiteLocations(Map<String,Object> map);
	
	/**查询编号是否已存在*/
	public boolean numberExists(Map<String,Object> map);
}

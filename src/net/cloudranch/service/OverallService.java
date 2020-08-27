package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Overall;

public interface OverallService {
	
	/**添加全景图信息*/
	public boolean addOverall(Overall overall);
	
	/**删除全景图信息*/
	public boolean delOverall(int id);
	
	/**修改全景图信息*/
	public boolean modifiedOverall(Map<String,Object> map);
	
	/**查询全景图信息*/
	public List<Overall> queryOveralls(Map<String,Object> map);
	
}

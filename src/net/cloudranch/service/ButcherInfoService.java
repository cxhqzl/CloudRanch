package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.ButcherInfo;

public interface ButcherInfoService {
	/**添加记录*/
	public boolean add(ButcherInfo butcherInfo);
	
	/**删除记录*/
	public boolean del(String unitId);
	
	/**修改*/
	public boolean modified(Map<String,Object> map);
	
	/**查询*/
	public ButcherInfo query(Map<String,Object> map);
	
	/**查询已注册ID*/
	public Map<String,Object> queryUnitIdByAccount(Map<String,Object> map);
	
	/**查询符合条件的所有信息*/
	public List<ButcherInfo> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**查询记录总数*/
	public int queryCount(Map<String,Object> map);
	
	/**查询ID是否存在*/
	public boolean queryUnitIdExist(Map<String,Object> map);
}

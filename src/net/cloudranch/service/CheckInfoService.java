package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.CheckInfo;

public interface CheckInfoService {
	/**添加记录*/
	public boolean add(CheckInfo checkInfo);
	
	/**删除记录*/
	public boolean del(int checkInfoId);
	
	/**修改*/
	public boolean modified(Map<String,Object> map);
	
	/**查询*/
	public CheckInfo query(Map<String,Object> map);
	
	/**查询符合条件的所有记录*/
	public List<CheckInfo> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**查询记录总数*/
	public int queryCount(Map<String,Object> map);
}

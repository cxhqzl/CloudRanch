package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Butcher;

public interface ButcherService {
	/**添加记录*/
	public boolean add(Butcher butcher);
	
	/**删除记录*/
	public boolean del(int butcherId);
	
	/**修改*/
	public boolean modified(Map<String,Object> map);
	
	/**查询*/
	public Butcher query(Map<String,Object> map);
	
	/**查询日期和备注*/
	public List<Butcher> queryDateAndRemarks(Map<String,Object> map);
	
	/**查询屠宰信息*/
	public List<Butcher> queryAll(Map<String,Object> map);
	
}

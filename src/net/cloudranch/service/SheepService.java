package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Sheep;

public interface SheepService {
	
	/**添加*/
	public boolean add(Sheep sheep);
	
	/**删除*/
	public boolean del(String sheepId);
	
	/**修改*/
	public boolean modified(Map<String,Object> map);
	
	/**查询*/
	public Sheep query(Map<String,Object> map);
	
	/**查询当天已录入的羊编号*/
	public Map<String, Object> querySheepId(Map<String,Object> map);
	
	/**查询羊ID是否已录入*/
	public boolean weatherSheepId(Map<String,Object> map);
	
	/**查询符合条件的所有羊信息*/
	public List<Sheep> queryByPlaceIdAndAccount(Map<String,Object> map);
	
	/**查询记录总数*/
	public int queryCount(Map<String,Object> map);
	
	
	/**获取经纬度*/
	public Map<String,Double> getLngAndLat(Map<String,Object> map);
	
	
	/**查询id是否存在*/
	public boolean querySheepIdExist(Map<String,Object> map);
	
}

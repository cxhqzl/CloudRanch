package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Forage;

public interface ForageService {
	/**增加数据*/
	public boolean add(Forage forage);
	
	/**删除数据*/
	public boolean del(int forageId);
	
	/**修改数据*/
	public boolean modified(Map<String,Object> map);
	
	/**查询数据*/
	public List<Forage> query(Map<String,Object> map);
	
	/**获取饲料名*/
	public String getForageName(Map<String,Object> map);
	
}

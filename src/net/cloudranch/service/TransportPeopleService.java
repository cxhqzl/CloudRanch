package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.TransportPeople;

public interface TransportPeopleService {
	/**添加*/
	public boolean add(TransportPeople tp);
	
	/**查询*/
	public List<TransportPeople> quertByAccount(Map<String,Object> map);
	
	/**修改资料*/
	public boolean modifiData(Map<String,Object> map);
	
	/**删除*/
	public boolean del(int peopleId);
}

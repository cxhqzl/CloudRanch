package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.TransportCompany;

public interface TransportCompanyService {
	/**添加*/
	public boolean add(TransportCompany transportCompany);
	
	/**查询*/
	public List<TransportCompany> quertByAccount(Map<String,Object> map);
	
	/**修改资料*/
	public boolean modifiData(Map<String,Object> map);
	
	/**删除*/
	public boolean del(int companyId);
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.AccountInfo;

public interface AccountInfoService {
	
	/**添加记录*/
	public boolean add(AccountInfo accountInfo);
	
	/**删除记录*/
	public boolean del(String account);
	
	/**查询信息*/
	public List<AccountInfo> query(Map<String,Object> map);
	
}

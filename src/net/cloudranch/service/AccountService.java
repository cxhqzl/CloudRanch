package net.cloudranch.service;

import java.util.Map;

import net.cloudranch.domain.Account;

public interface AccountService {
	
	/**注册新用户*/
	public boolean register(Account account);
	
	/**删除用户*/
	public boolean del(String account);
	
	/**修改密码或用户等级*/
	public boolean modifiedAccount(Map<String,Object> map);
	
	/**匹配账号*/
	public boolean checkAccount(Map<String,Object> map);
	
	/**匹配密码*/
	public boolean checkPassword(Map<String,Object> map);
	
	/**查询用户等级*/
	public int queryAccountRole(Map<String,Object> map);
	
}

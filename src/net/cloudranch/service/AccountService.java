package net.cloudranch.service;

import java.util.Map;

import net.cloudranch.domain.Account;

public interface AccountService {
	
	/**ע�����û�*/
	public boolean register(Account account);
	
	/**ɾ���û�*/
	public boolean del(String account);
	
	/**�޸�������û��ȼ�*/
	public boolean modifiedAccount(Map<String,Object> map);
	
	/**ƥ���˺�*/
	public boolean checkAccount(Map<String,Object> map);
	
	/**ƥ������*/
	public boolean checkPassword(Map<String,Object> map);
	
	/**��ѯ�û��ȼ�*/
	public int queryAccountRole(Map<String,Object> map);
	
}

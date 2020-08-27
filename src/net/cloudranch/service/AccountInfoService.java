package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.AccountInfo;

public interface AccountInfoService {
	
	/**��Ӽ�¼*/
	public boolean add(AccountInfo accountInfo);
	
	/**ɾ����¼*/
	public boolean del(String account);
	
	/**��ѯ��Ϣ*/
	public List<AccountInfo> query(Map<String,Object> map);
	
}

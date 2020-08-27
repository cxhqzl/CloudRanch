package net.cloudranch.service;

import java.util.Map;

import net.cloudranch.domain.LoginLog;

public interface LoginLogService {
	
	/**���*/
	public boolean addLog(LoginLog loginLog);
	
	/**�޸�*/
	public boolean modifiLog(LoginLog loginLog);
	
	/**��ѯ�˺���־�Ƿ��Ѽ�¼*/
	public boolean isLogForAccount(Map<String,Object> map);
	
	/**��ѯ��־����*/
	public int getLogType(Map<String,Object> map);
	
	/**��ѯ*/
	public LoginLog queryLog(Map<String,Object> map);
}

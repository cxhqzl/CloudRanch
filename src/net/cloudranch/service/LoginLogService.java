package net.cloudranch.service;

import java.util.Map;

import net.cloudranch.domain.LoginLog;

public interface LoginLogService {
	
	/**添加*/
	public boolean addLog(LoginLog loginLog);
	
	/**修改*/
	public boolean modifiLog(LoginLog loginLog);
	
	/**查询账号日志是否已记录*/
	public boolean isLogForAccount(Map<String,Object> map);
	
	/**查询日志类型*/
	public int getLogType(Map<String,Object> map);
	
	/**查询*/
	public LoginLog queryLog(Map<String,Object> map);
}

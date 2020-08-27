package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.LoginLogDao;
import net.cloudranch.domain.LoginLog;
import net.cloudranch.service.LoginLogService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	private LoginLogDao loginLogDao;

	@Override
	public boolean addLog(LoginLog loginLog) {
		int res = loginLogDao.insert(loginLog);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifiLog(LoginLog loginLog) {
		int res = loginLogDao.update(loginLog);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isLogForAccount(Map<String,Object> map) {
		List<LoginLog> logs = loginLogDao.select(map);
		if(logs.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int getLogType(Map<String, Object> map) {
		List<LoginLog> logs = loginLogDao.select(map);
		return logs.get(0).getType();
	}

	@Override
	public LoginLog queryLog(Map<String, Object> map) {
		List<LoginLog> logs = loginLogDao.select(map);
		return logs.get(0);
	}
	

}

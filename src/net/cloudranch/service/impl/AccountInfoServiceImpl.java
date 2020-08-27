package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.AccountInfoDao;
import net.cloudranch.domain.AccountInfo;
import net.cloudranch.service.AccountInfoService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {

	@Autowired
	private AccountInfoDao accountInfoDao;

	@Override
	public boolean add(AccountInfo accountInfo) {
		int res = accountInfoDao.insert(accountInfo);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean del(String account) {
		int res = accountInfoDao.delete(account);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<AccountInfo> query(Map<String, Object> map) {
		return accountInfoDao.select(map);
	}
}

package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.AccountDao;
import net.cloudranch.domain.Account;
import net.cloudranch.service.AccountService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public boolean del(String account) {
		int res = accountDao.delete(account);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modifiedAccount(Map<String, Object> map) {
		int res = accountDao.update(map);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean checkAccount(Map<String, Object> map) {
		List<Account> accounts = accountDao.select(map);
		if(accounts == null || accounts.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean checkPassword(Map<String, Object> map) {
		List<Account> accounts = accountDao.select(map);
		if(accounts == null || accounts.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public int queryAccountRole(Map<String, Object> map) {
		List<Account> accounts = accountDao.select(map);
		if(accounts == null || accounts.size() == 0) {
			return -1;
		}else {
			return accounts.get(0).getRole();
		}
	}

	@Override
	public boolean register(Account account) {
		int res = accountDao.insert(account);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

}

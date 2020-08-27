package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.H2sDao;
import net.cloudranch.domain.H2s;
import net.cloudranch.service.H2sService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("h2sService")
public class H2sServiceImpl implements H2sService {

	@Autowired
	private H2sDao h2sDao;

	@Override
	public boolean add(H2s h2s) {
		int res = h2sDao.insert(h2s);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = h2sDao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<H2s> query(Map<String, Object> map) {
		return h2sDao.select(map);
	}

	
}

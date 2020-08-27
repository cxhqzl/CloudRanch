package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.ParDao;
import net.cloudranch.domain.Par;
import net.cloudranch.service.ParService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("parService")
public class ParServiceImpl implements ParService {

	@Autowired
	private ParDao parDao;

	@Override
	public boolean add(Par par) {
		int res = parDao.insert(par);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = parDao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Par> query(Map<String, Object> map) {
		return parDao.select(map);
	}


}

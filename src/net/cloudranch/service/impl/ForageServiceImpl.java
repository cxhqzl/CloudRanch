package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.ForageDao;
import net.cloudranch.domain.Forage;
import net.cloudranch.service.ForageService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("forageService")
public class ForageServiceImpl implements ForageService {

	@Autowired
	private ForageDao forageDao;
	
	@Override
	public boolean add(Forage forage) {
		int res = forageDao.insert(forage);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean del(int forageId) {
		int res = forageDao.delete(forageId);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = forageDao.update(map);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Forage> query(Map<String, Object> map) {
		return forageDao.select(map);
	}

	@Override
	public String getForageName(Map<String, Object> map) {
		return forageDao.select(map).get(0).getForageName();
	}

}

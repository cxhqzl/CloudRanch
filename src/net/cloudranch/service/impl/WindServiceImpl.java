package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.WindDao;
import net.cloudranch.domain.Wind;
import net.cloudranch.service.WindService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("windService")
public class WindServiceImpl implements WindService {

	@Autowired
	private WindDao windDao;

	@Override
	public boolean add(Wind wind) {
		int res = windDao.insert(wind);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = windDao.delete(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Wind> query(Map<String, Object> map) {
		return windDao.select(map);
	}
	

}

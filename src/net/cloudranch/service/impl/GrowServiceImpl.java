package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.GrowDao;
import net.cloudranch.domain.Grow;
import net.cloudranch.service.GrowService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("growService")
public class GrowServiceImpl implements GrowService {

	@Autowired
	private GrowDao growDao;
	
	@Override
	public boolean addGrow(Grow grow) {
		int res = growDao.insert(grow);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delGrow(int id) {
		int res = growDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedGrow(Map<String, Object> map) {
		int res = growDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Grow> queryGrows(Map<String, Object> map) {
		return growDao.select(map);
	}

}

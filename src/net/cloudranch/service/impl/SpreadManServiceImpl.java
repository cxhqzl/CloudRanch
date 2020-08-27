package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SpreadManDao;
import net.cloudranch.domain.SpreadMan;
import net.cloudranch.service.SpreadManService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("spreadManService")
public class SpreadManServiceImpl implements SpreadManService {

	@Autowired
	private SpreadManDao spreadManDao;
	
	@Override
	public boolean addSpreadMan(SpreadMan spreadMan) {
		int res = spreadManDao.insert(spreadMan);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delSpreadMan(int id) {
		int res = spreadManDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedSpreadMan(Map<String, Object> map) {
		int res = spreadManDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<SpreadMan> querySpreadMans(Map<String, Object> map) {
		return spreadManDao.select(map);
	}

}

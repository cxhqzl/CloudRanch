package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.OverallDao;
import net.cloudranch.domain.Overall;
import net.cloudranch.service.OverallService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("overallService")
public class OverallServiceImpl implements OverallService {

	@Autowired
	private OverallDao overallDao;

	@Override
	public boolean addOverall(Overall overall) {
		int res = overallDao.insert(overall);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delOverall(int id) {
		int res = overallDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedOverall(Map<String, Object> map) {
		int res = overallDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Overall> queryOveralls(Map<String, Object> map) {
		return overallDao.select(map);
	}
	
}

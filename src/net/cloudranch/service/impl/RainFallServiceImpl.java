package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.RainFallDao;
import net.cloudranch.domain.RainFall;
import net.cloudranch.service.RainFallService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("rainFallService")
public class RainFallServiceImpl implements RainFallService {

	@Autowired
	private RainFallDao rainFallDao;

	@Override
	public boolean add(RainFall rainFall) {
		int res = rainFallDao.insert(rainFall);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = rainFallDao.delete(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<RainFall> query(Map<String, Object> map) {
		return rainFallDao.select(map);
	}
	
}

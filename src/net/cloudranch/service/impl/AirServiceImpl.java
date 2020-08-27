package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.AirDao;
import net.cloudranch.domain.Air;
import net.cloudranch.service.AirService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("airService")
public class AirServiceImpl implements AirService {

	@Autowired
	private AirDao airDao;
	
	@Override
	public boolean add(Air air) {
		int res = airDao.insert(air);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = airDao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Air> query(Map<String, Object> map) {
		return airDao.select(map);
	}

}

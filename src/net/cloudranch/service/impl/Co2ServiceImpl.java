package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.Co2Dao;
import net.cloudranch.domain.Co2;
import net.cloudranch.service.Co2Service;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("co2Service")
public class Co2ServiceImpl implements Co2Service {

	@Autowired
	private Co2Dao co2Dao;

	@Override
	public boolean add(Co2 co2) {
		int res = co2Dao.insert(co2);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = co2Dao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Co2> query(Map<String, Object> map) {
		return co2Dao.select(map);
	}
	

}

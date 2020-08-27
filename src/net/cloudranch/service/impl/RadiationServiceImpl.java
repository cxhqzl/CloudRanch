package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.RadiationDao;
import net.cloudranch.domain.Radiation;
import net.cloudranch.service.RadiationService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("radiationService")
public class RadiationServiceImpl implements RadiationService {

	@Autowired
	private RadiationDao radiationDao;

	@Override
	public boolean add(Radiation radiation) {
		int res = radiationDao.insert(radiation);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = radiationDao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Radiation> query(Map<String, Object> map) {
		return radiationDao.select(map);
	}
}

package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SoilDao;
import net.cloudranch.domain.Soil;
import net.cloudranch.service.SoilService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("soilService")
public class SoilServiceImpl implements SoilService {

	@Autowired
	private SoilDao soilDao;

	@Override
	public boolean add(Soil soil) {
		int res = soilDao.insert(soil);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = soilDao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Soil> query(Map<String, Object> map) {
		return soilDao.select(map);
	}
	

}

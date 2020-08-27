package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.PlaceSensorDao;
import net.cloudranch.domain.PlaceSensor;
import net.cloudranch.service.PlaceSensorService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("placeSensorService")
public class PlaceSensorServiceImpl implements PlaceSensorService {

	@Autowired
	private PlaceSensorDao placeSensorDao;

	@Override
	public boolean addPlaceSensor(PlaceSensor placeSensor) {
		int res = placeSensorDao.insert(placeSensor);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean del(int id) {
		int res = placeSensorDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedPlaceSensor(Map<String, Object> map) {
		int res = placeSensorDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<PlaceSensor> queryPlaceSensors(Map<String, Object> map) {
		return placeSensorDao.select(map);
	}
	

}

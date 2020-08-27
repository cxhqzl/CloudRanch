package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SensorDao;
import net.cloudranch.domain.Sensor;
import net.cloudranch.service.SensorService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("sensorService")
public class SensorServiceImpl implements SensorService {

	@Autowired
	private SensorDao sensorDao;

	@Override
	public boolean addSensor(Sensor sensor) {
		int res = sensorDao.insert(sensor);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delSensor(int sensorId) {
		int res = sensorDao.delete(sensorId);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiSensor(Map<String, Object> map) {
		int res = sensorDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Sensor> querySensors(Map<String, Object> map) {
		return sensorDao.select(map);
	}
}

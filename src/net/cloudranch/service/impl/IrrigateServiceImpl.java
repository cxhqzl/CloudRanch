package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.IrrigateDao;
import net.cloudranch.domain.Irrigate;
import net.cloudranch.service.IrrigateService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("irrigateService")
public class IrrigateServiceImpl implements IrrigateService {

	@Autowired
	private IrrigateDao irrigateDao;
	
	@Override
	public boolean addIrrigate(Irrigate irrigate) {
		int res = irrigateDao.insert(irrigate);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delIrrigate(int id) {
		int res = irrigateDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedIrrigate(Map<String, Object> map) {
		int res = irrigateDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Irrigate> queryIrrigates(Map<String, Object> map) {
		return irrigateDao.select(map);
	}

}

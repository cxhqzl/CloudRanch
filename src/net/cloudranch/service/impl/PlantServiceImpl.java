package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.PlantDao;
import net.cloudranch.domain.Plant;
import net.cloudranch.service.PlantService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("plantService")
public class PlantServiceImpl implements PlantService {

	@Autowired
	private PlantDao plantDao;

	@Override
	public boolean addPlant(Plant plant) {
		int res = plantDao.insert(plant);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delPlant(String cropId) {
		int res = plantDao.delete(cropId);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiPlant(Map<String, Object> map) {
		int res = plantDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Plant> queryPlant(Map<String, Object> map) {
		return plantDao.select(map);
	}
	
}

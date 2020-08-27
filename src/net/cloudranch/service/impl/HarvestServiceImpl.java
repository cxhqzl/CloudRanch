package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.HarvestDao;
import net.cloudranch.domain.Harvest;
import net.cloudranch.service.HarvestService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("harvestService")
public class HarvestServiceImpl implements HarvestService {

	@Autowired
	private HarvestDao harvestDao;
	
	@Override
	public boolean addHarvest(Harvest harvest) {
		int res = harvestDao.insert(harvest);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delHarvest(int id) {
		int res = harvestDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedHarvest(Map<String, Object> map) {
		int res = harvestDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Harvest> queryHarvests(Map<String, Object> map) {
		return harvestDao.select(map);
	}

}

package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.PesticideDao;
import net.cloudranch.domain.Pesticide;
import net.cloudranch.service.PesticideService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("pesticideService")
public class PesticideServiceImpl implements PesticideService {

	@Autowired
	private PesticideDao pesticideDao;
	
	@Override
	public boolean addPesticide(Pesticide pesticide) {
		int res = pesticideDao.insert(pesticide);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delPesticide(int id) {
		int res = pesticideDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedPesticide(Map<String, Object> map) {
		int res = pesticideDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Pesticide> queryPesticides(Map<String, Object> map) {
		return pesticideDao.select(map);
	}

}

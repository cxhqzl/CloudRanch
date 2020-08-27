package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.PlaceLocationDao;
import net.cloudranch.domain.PlaceLocation;
import net.cloudranch.service.PlaceLocationService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("placeLocationService")
public class PlaceLocationServiceImpl implements PlaceLocationService {

	@Autowired
	private PlaceLocationDao placeLocationDao;
	
	@Override
	public boolean addPlaceLocation(PlaceLocation placeLocation) {
		int res = placeLocationDao.insert(placeLocation);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delPlaceLocation(int id) {
		int res = placeLocationDao.delete(id);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifiyPlaceLocation(Map<String, Object> map) {
		int res = placeLocationDao.update(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<PlaceLocation> queryPlaceLocations(Map<String, Object> map) {
		return placeLocationDao.select(map);
	}

	@Override
	public boolean numberExists(Map<String, Object> map) {
		List<PlaceLocation> sls = placeLocationDao.select(map);
		if(sls.size()>0) {
			return true;
		}else {
			return false;
		}
	}

}

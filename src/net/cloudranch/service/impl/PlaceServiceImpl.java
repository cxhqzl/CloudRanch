package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.PlaceDao;
import net.cloudranch.domain.Place;
import net.cloudranch.service.PlaceService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("placeService")
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;

	@Override
	public boolean addPlace(Place place) {
		int res = placeDao.insert(place);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delPlace(int placeId) {
		int res = placeDao.delete(placeId);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiPlace(Map<String, Object> map) {
		int res = placeDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Place> queryPlaces(Map<String, Object> map) {
		return placeDao.select(map);
	}
	
	
}

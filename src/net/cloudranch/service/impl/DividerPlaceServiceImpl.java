package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.DividerPlaceDao;
import net.cloudranch.domain.DividerPlace;
import net.cloudranch.service.DividerPlaceService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("dividerPlaceService")
public class DividerPlaceServiceImpl implements DividerPlaceService {

	@Autowired
	private DividerPlaceDao dividerPlaceDao;

	@Override
	public boolean addDividerPlace(DividerPlace dividerPlace) {
		int res = dividerPlaceDao.insert(dividerPlace);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delDividerPlace(int dividerId) {
		int res = dividerPlaceDao.delete(dividerId);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiDividerPlace(Map<String, Object> map) {
		int res = dividerPlaceDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<DividerPlace> queryDividerPlaces(Map<String, Object> map) {
		return dividerPlaceDao.select(map);
	}

}

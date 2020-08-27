package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SiteLocationDao;
import net.cloudranch.domain.SiteLocation;
import net.cloudranch.service.SiteLocationService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("siteLocationService")
public class SiteLocationServiceImpl implements SiteLocationService {

	@Autowired
	private SiteLocationDao siteLocationDao;
	
	@Override
	public boolean addSiteLocation(SiteLocation siteLocation) {
		int res = siteLocationDao.insert(siteLocation);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delSiteLocation(int id) {
		int res = siteLocationDao.delete(id);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifiySiteLocation(Map<String, Object> map) {
		int res = siteLocationDao.update(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<SiteLocation> querySiteLocations(Map<String, Object> map) {
		return siteLocationDao.select(map);
	}

	@Override
	public boolean numberExists(Map<String, Object> map) {
		List<SiteLocation> sls = siteLocationDao.select(map);
		if(sls.size()>0) {
			return true;
		}else {
			return false;
		}
	}

}

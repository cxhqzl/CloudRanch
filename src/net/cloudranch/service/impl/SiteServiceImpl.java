package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SiteDao;
import net.cloudranch.domain.Site;
import net.cloudranch.service.SiteService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("siteService")
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteDao siteDao;
	
	@Override
	public boolean addSite(Site site) {
		int res = siteDao.insert(site);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delSite(int siteId) {
		int res = siteDao.delete(siteId);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifiySiteToAccount(Map<String, Object> map) {
		int res = siteDao.update(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Site querySite(Map<String, Object> map) {
		List<Site> sites = siteDao.select(map);
		if(sites.size() > 0) {
			return sites.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Site> searchSites(Map<String, Object> map) {
		return siteDao.select(map);
	}

	@Override
	public Site getOneSite(Map<String, Object> map) {
		List<Site> sites = siteDao.select(map);
		int size = sites.size();
		int num = (int) (Math.floor(Math.random()*size));
		return sites.get(num);
	}

	@Override
	public boolean siteClaim(Map<String, Object> map) {
		List<Site> sites = siteDao.select(map);
		if(sites.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifiSite(Map<String, Object> map) {
		int res = siteDao.update(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Site> adminGetSites(Map<String, Object> map) {
		return siteDao.select(map);
	}

}

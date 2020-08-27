package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SiteImageDao;
import net.cloudranch.domain.SiteImage;
import net.cloudranch.service.SiteImageService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("siteImageService")
public class SiteImageServiceImpl implements SiteImageService {

	@Autowired
	private SiteImageDao siteImageDao;
	
	@Override
	public boolean addSiteImage(SiteImage siteImage) {
		int res = siteImageDao.insert(siteImage);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delSiteImage(int id) {
		int res = siteImageDao.delete(id);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifiySiteImage(Map<String, Object> map) {
		int res = siteImageDao.update(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<SiteImage> querySiteImages(Map<String, Object> map) {
		return siteImageDao.select(map);
	}

}

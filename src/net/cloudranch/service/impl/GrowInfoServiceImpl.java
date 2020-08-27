package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.GrowInfoDao;
import net.cloudranch.domain.GrowInfo;
import net.cloudranch.service.GrowInfoService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("growInfoService")
public class GrowInfoServiceImpl implements GrowInfoService {

	@Autowired
	private GrowInfoDao growInfoDao;
	
	@Override
	public boolean add(GrowInfo growInfo) {
		int res = growInfoDao.insert(growInfo);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean del(int growInfoId) {
		int res = growInfoDao.delete(growInfoId);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = growInfoDao.update(map);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public GrowInfo query(Map<String, Object> map) {
		List<GrowInfo> growInfos = growInfoDao.select(map);
		if(growInfos.size() == 0) {
			return null;
		}else {
			return growInfos.get(0);
		}
	}

	@Override
	public String queryImageBySheepId(Map<String, Object> map) {
		List<GrowInfo> growInfos = growInfoDao.select(map);
		if(growInfos.size() == 0) {
			return null;
		}else {
			return growInfos.get(growInfos.size()-1).getImage();
		}
	}

	@Override
	public List<GrowInfo> queryBySheepIdAndAccount(Map<String, Object> map) {
		return growInfoDao.select(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return growInfoDao.select(map).size();
	}

	@Override
	public String getLastImage(Map<String, Object> map) {
		List<GrowInfo> gis = growInfoDao.select(map);
		String image = "";
		for(int i=gis.size()-1;i>=0;i--) {
			image = gis.get(i).getImage();
			if(image == null || image.equals("")) {
				continue;
			}else {
				break;
			}
		}
		return image;
	}

}

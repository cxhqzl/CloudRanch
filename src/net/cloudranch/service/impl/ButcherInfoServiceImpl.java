package net.cloudranch.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.ButcherInfoDao;
import net.cloudranch.domain.ButcherInfo;
import net.cloudranch.service.ButcherInfoService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("butcherInfoService")
public class ButcherInfoServiceImpl implements ButcherInfoService{
	@Autowired
	private ButcherInfoDao butcherInfoDao;
	
	@Override
	public boolean add(ButcherInfo butcherInfo) {
		int res = butcherInfoDao.insert(butcherInfo);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean del(String unitId) {
		int res = butcherInfoDao.delete(unitId);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = butcherInfoDao.update(map);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public ButcherInfo query(Map<String, Object> map) {
		List<ButcherInfo> listButcherInfo = butcherInfoDao.select(map);
		if(listButcherInfo.size()==0) {
			return null;
		}else {
			return listButcherInfo.get(0);
		}
	}

	@Override
	public Map<String,Object> queryUnitIdByAccount(Map<String, Object> map) {
		List<ButcherInfo> listButcherInfo = butcherInfoDao.select(map);
		if(listButcherInfo.size() == 0) {
			return null;
		}else {
			Map<String,Object> unitIds = new HashMap<String,Object>();
			for(int i=0;i<listButcherInfo.size();i++) {
				unitIds.put(listButcherInfo.get(i).getUnitId(), 0);
			}
			return unitIds;
		}
	}

	@Override
	public List<ButcherInfo> queryBySheepIdAndAccount(Map<String, Object> map) {
		return butcherInfoDao.select(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return butcherInfoDao.select(map).size();
	}

	@Override
	public boolean queryUnitIdExist(Map<String, Object> map) {
		List<ButcherInfo> bis = butcherInfoDao.select(map);
		if(bis.size()<=0) {
			return false;
		}else {
			return true;
		}
	}

}

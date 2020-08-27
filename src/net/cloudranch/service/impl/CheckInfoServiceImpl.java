package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.CheckInfoDao;
import net.cloudranch.domain.CheckInfo;
import net.cloudranch.service.CheckInfoService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("checkInfoService")
public class CheckInfoServiceImpl implements CheckInfoService {
	@Autowired
	private CheckInfoDao checkInfoDao;
	
	@Override
	public boolean add(CheckInfo checkInfo) {
		int res  = checkInfoDao.insert(checkInfo);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean del(int checkInfoId) {
		int res  = checkInfoDao.delete(checkInfoId);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res  = checkInfoDao.update(map);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public CheckInfo query(Map<String, Object> map) {
		List<CheckInfo> listCheckInfo = checkInfoDao.select(map);
		if(listCheckInfo.size()==0) {
			return null;
		}else {
			return listCheckInfo.get(0);
		}
	}

	@Override
	public List<CheckInfo> queryBySheepIdAndAccount(Map<String, Object> map) {
		return checkInfoDao.select(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return checkInfoDao.select(map).size();
	}

}

package net.cloudranch.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SheepDao;
import net.cloudranch.domain.Sheep;
import net.cloudranch.service.SheepService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("sheepService")
public class SheepServiceImpl implements SheepService {

	@Autowired
	private SheepDao sheepDao;
	
	@Override
	public boolean add(Sheep sheep) {
		boolean flag = false;
		int res = sheepDao.insert(sheep);
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean del(String sheepId) {
		boolean flag = false;
		int res = sheepDao.delete(sheepId);
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		boolean flag = false;
		int res = sheepDao.update(map);
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Sheep query(Map<String, Object> map) {
		List<Sheep> sheeps = sheepDao.select(map);
		if(sheeps.size()==0) {
			return null;
		}else {
			return sheeps.get(0);
		}
	}

	@Override
	public Map<String, Object> querySheepId(Map<String, Object> map) {
		List<Sheep> sheeps = sheepDao.select(map);
		int len = sheeps.size();
		if(len == 0) {
			return null;
		}else {
			Map<String,Object> sheepIds = new HashMap<String,Object>();
			for(int i=0;i<len;i++) {
				sheepIds.put(sheeps.get(i).getSheepId(), 0);
			}
			return sheepIds;
		}
	}

	@Override
	public boolean weatherSheepId(Map<String, Object> map) {
		List<Sheep> sheeps = sheepDao.select(map);
		if(sheeps.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Sheep> queryByPlaceIdAndAccount(Map<String, Object> map) {
		return sheepDao.select(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return sheepDao.select(map).size();
	}

	@Override
	public Map<String, Double> getLngAndLat(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean querySheepIdExist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}
}

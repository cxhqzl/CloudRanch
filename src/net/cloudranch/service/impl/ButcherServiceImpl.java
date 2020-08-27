package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.ButcherDao;
import net.cloudranch.domain.Butcher;
import net.cloudranch.service.ButcherService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("butcherService")
public class ButcherServiceImpl implements ButcherService{
	@Autowired
	private ButcherDao butcherDao;

	@Override
	public boolean add(Butcher butcher) {
		int res = butcherDao.insert(butcher);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean del(int butcherId) {
		int res = butcherDao.delete(butcherId);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = butcherDao.update(map);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Butcher query(Map<String, Object> map) {
		List<Butcher> listButcher = butcherDao.select(map);
		if(listButcher.size()==0) {
			return null;
		}else {
			return listButcher.get(0);
		}
	}

	@Override
	public List<Butcher> queryDateAndRemarks(Map<String, Object> map) {
		return  butcherDao.select(map);
	}

	@Override
	public List<Butcher> queryAll(Map<String, Object> map) {
		return butcherDao.select(map);
	}

}

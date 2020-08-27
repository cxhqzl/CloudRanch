package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.WogDao;
import net.cloudranch.domain.Wog;
import net.cloudranch.service.WogService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("wogService")
public class WogServiceImpl implements WogService {

	@Autowired
	private WogDao wogDao;
	
	@Override
	public boolean addWog(Wog wog) {
		int res = wogDao.insert(wog);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delWog(int id) {
		int res = wogDao.delete(id);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiedWog(Map<String, Object> map) {
		int res = wogDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Wog> queryWogs(Map<String, Object> map) {
		return wogDao.select(map);
	}

}

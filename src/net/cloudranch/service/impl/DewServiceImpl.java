package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.DewDao;
import net.cloudranch.domain.Dew;
import net.cloudranch.service.DewService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("dewService")
public class DewServiceImpl implements DewService {

	@Autowired
	private DewDao dewDao;

	@Override
	public boolean add(Dew dew) {
		int res = dewDao.insert(dew);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = dewDao.delete(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Dew> query(Map<String, Object> map) {
		return dewDao.select(map);
	}
	

}

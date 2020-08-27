package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.TransportPeopleDao;
import net.cloudranch.domain.TransportPeople;
import net.cloudranch.service.TransportPeopleService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("transportPeopleService")
public class TransportPeopleServiceImpl implements TransportPeopleService {
	
	@Autowired
	private TransportPeopleDao transportPeopleDao;

	@Override
	public boolean add(TransportPeople tp) {
		int res = transportPeopleDao.insert(tp);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<TransportPeople> quertByAccount(Map<String, Object> map) {
		return transportPeopleDao.select(map);
	}

	@Override
	public boolean del(int peopleId) {
		int res = transportPeopleDao.delete(peopleId);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modifiData(Map<String, Object> map) {
		int res = transportPeopleDao.update(map);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

}

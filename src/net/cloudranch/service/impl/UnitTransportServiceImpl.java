package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.UnitTransportDao;
import net.cloudranch.domain.UnitTransport;
import net.cloudranch.service.UnitTransportService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("unitTransportService")
public class UnitTransportServiceImpl implements UnitTransportService {
	
	@Autowired
	private UnitTransportDao unitTransportDao;

	@Override
	public boolean add(UnitTransport ut) {
		int res = unitTransportDao.insert(ut);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<UnitTransport> getUnitTransport(Map<String, Object> map) {
		return  unitTransportDao.select(map);
	}

}

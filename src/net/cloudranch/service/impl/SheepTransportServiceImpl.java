package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.SheepTransportDao;
import net.cloudranch.domain.SheepTransport;
import net.cloudranch.service.SheepTransportService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("sheepTransportService")
public class SheepTransportServiceImpl implements SheepTransportService {
	
	@Autowired
	private SheepTransportDao sheepTransportDao;

	@Override
	public boolean add(SheepTransport st) {
		int res = sheepTransportDao.insert(st);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public SheepTransport getSheepTransport(Map<String, Object> map) {
		List<SheepTransport> sts = sheepTransportDao.select(map);
		if(sts.size() == 0) {
			return null;
		}else {
			return sts.get(0);
		}
	}

}

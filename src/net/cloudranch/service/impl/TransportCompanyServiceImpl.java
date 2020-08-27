package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.TransportCompanyDao;
import net.cloudranch.domain.TransportCompany;
import net.cloudranch.service.TransportCompanyService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("transportCompanyService")
public class TransportCompanyServiceImpl implements TransportCompanyService {
	
	@Autowired
	private TransportCompanyDao transportCompanyDao;

	@Override
	public boolean add(TransportCompany transportCompany) {
		int res = transportCompanyDao.insert(transportCompany);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<TransportCompany> quertByAccount(Map<String, Object> map) {
		return transportCompanyDao.select(map);
	}

	@Override
	public boolean del(int companyId) {
		int res = transportCompanyDao.delete(companyId);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modifiData(Map<String, Object> map) {
		int res = transportCompanyDao.update(map);
		if(res == 0) {
			return false;
		}else {
			return true;
		}
	}

}

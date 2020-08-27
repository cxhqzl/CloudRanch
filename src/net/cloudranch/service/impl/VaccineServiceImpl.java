package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.VaccineDao;
import net.cloudranch.domain.Vaccine;
import net.cloudranch.service.VaccineService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("vaccineService")
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineDao vaccineDao;
	
	@Override
	public boolean add(Vaccine vaccine) {
		int res = vaccineDao.insert(vaccine);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean del(int vaccineId) {
		int res = vaccineDao.delete(vaccineId);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = vaccineDao.update(map);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Vaccine> query(Map<String, Object> map) {
		return vaccineDao.select(map);
	}

	@Override
	public Vaccine getVaccineName(Map<String, Object> map) {
		return vaccineDao.select(map).get(0);
	}

}

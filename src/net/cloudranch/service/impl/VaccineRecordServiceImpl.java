package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.VaccineRecordDao;
import net.cloudranch.domain.VaccineRecord;
import net.cloudranch.service.VaccineRecordService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("vaccineRecordService")
public class VaccineRecordServiceImpl implements VaccineRecordService {
	@Autowired
	private VaccineRecordDao vaccineRecordDao;
	
	@Override
	public boolean add(VaccineRecord vaccineRecord) {
		int res = vaccineRecordDao.insert(vaccineRecord);
		boolean flag =false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean del(int vaccineRecordId) {
		int res = vaccineRecordDao.delete(vaccineRecordId);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = vaccineRecordDao.update(map);
		boolean flag = false;
		if(res != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public VaccineRecord query(Map<String, Object> map) {
		List<VaccineRecord> listVaccineRecord = vaccineRecordDao.select(map);
		if(listVaccineRecord.size()==0) {
			return null;
		}else {
			return listVaccineRecord.get(0);
		}
		
	}

	@Override
	public List<VaccineRecord> queryBySheepIdAndAccount(Map<String, Object> map) {
		return vaccineRecordDao.select(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return vaccineRecordDao.select(map).size();
	}

}

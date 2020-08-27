package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.Nh3Dao;
import net.cloudranch.domain.Nh3;
import net.cloudranch.service.Nh3Service;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("nh3Service")
public class Nh3ServiceImpl implements Nh3Service {

	@Autowired
	private Nh3Dao nh3Dao;

	@Override
	public boolean add(Nh3 nh3) {
		int res = nh3Dao.insert(nh3);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		int res = nh3Dao.delete(map);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Nh3> query(Map<String, Object> map) {
		return nh3Dao.select(map);
	}
}

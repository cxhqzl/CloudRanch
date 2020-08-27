package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.VidoDao;
import net.cloudranch.domain.Vido;
import net.cloudranch.service.VidoService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("vidoService")
public class VidoServiceImpl implements VidoService {

	@Autowired
	private VidoDao vidoService;

	@Override
	public boolean addVido(Vido vido) {
		int res = vidoService.insert(vido);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean delVido(int id) {
		int res = vidoService.delete(id);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modifiedVido(Map<String, Object> map) {
		int res = vidoService.update(map);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Vido> queryVidos(Map<String, Object> map) {
		return vidoService.select(map);
	}
	
	
}

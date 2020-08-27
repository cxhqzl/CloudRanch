package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.NodeDao;
import net.cloudranch.domain.Node;
import net.cloudranch.service.NodeService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("nodeService")
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodeDao nodeDao;

	@Override
	public boolean addNode(Node node) {
		int res = nodeDao.insert(node);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delNode(int nodeId) {
		int res = nodeDao.delete(nodeId);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifiNode(Map<String, Object> map) {
		int res = nodeDao.update(map);
		if(res > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Node> queryNodes(Map<String, Object> map) {
		return nodeDao.select(map);
	}
	
	
}

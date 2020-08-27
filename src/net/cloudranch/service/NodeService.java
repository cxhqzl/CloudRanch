package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Node;

public interface NodeService {
	
	/**添加节点*/
	public boolean addNode(Node node);
	
	/**删除*/
	public boolean delNode(int nodeId);
	
	/**修改节点*/
	public boolean modifiNode(Map<String,Object> map);
	
	/**查询*/
	public List<Node> queryNodes(Map<String,Object> map);
}

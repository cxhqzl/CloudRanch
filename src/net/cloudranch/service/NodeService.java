package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Node;

public interface NodeService {
	
	/**��ӽڵ�*/
	public boolean addNode(Node node);
	
	/**ɾ��*/
	public boolean delNode(int nodeId);
	
	/**�޸Ľڵ�*/
	public boolean modifiNode(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Node> queryNodes(Map<String,Object> map);
}

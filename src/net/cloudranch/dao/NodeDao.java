package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Node;
import net.cloudranch.provider.NodeProvider;

public interface NodeDao {
	
	@Insert("INSERT INTO "
			+ "t_node(nodename,mac,type,interval,isreport,status,battery,createDate,placeid) "
			+ "values(#{nodeName},#{mac},#{type},#{interval},#{isReport},#{status},#{battery},#{createDate},#{placeId})")
	public int insert(Node node);
	
	@Delete("DELETE FROM t_node WHERE nodeid = #{nodeId}")
	public int delete(int nodeId);
	
	@UpdateProvider(type=NodeProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=NodeProvider.class,method="select")
	public List<Node> select(Map<String,Object> map);
	
}

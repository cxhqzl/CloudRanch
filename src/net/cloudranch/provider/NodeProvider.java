package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class NodeProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_node");
				if(map.get("nodeName") != null){
					SET("nodename = #{nodeName}");
				}
				if(map.get("mac") != null){
					SET("mac = #{mac}");
				}
				if(map.get("type") != null){
					SET("type = #{type}");
				}
				if(map.get("interval") != null){
					SET("interval = #{interval}");
				}
				if(map.get("isReport") != null){
					SET("isreport = #{isReport}");
				}
				if(map.get("status") != null){
					SET("status = #{status}");
				}
				if(map.get("battery") != null){
					SET("battery = #{battery}");
				}
				if(map.get("placeId") != null){
					SET("placeid = #{placeId}");
				}
				WHERE("nodeid = #{nodeId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_node");
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("nodeId") != null){
					WHERE("nodeid = #{nodeId}");
				}
			}
		}.toString();
		return sql;
	}
}

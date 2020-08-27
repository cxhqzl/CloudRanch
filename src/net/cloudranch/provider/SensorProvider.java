package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SensorProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_sensor");
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
				if(map.get("nodeId") != null){
					SET("nodeid = #{nodeId}");
				}
				if(map.get("picture") != null){
					SET("picture = #{picture}");
				}
				if(map.get("icon") != null){
					SET("icon = #{icon}");
				}
				WHERE("sensorid = #{sensorId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_sensor");
				if(map.get("sensorId") != null){
					WHERE("sensorid = #{sensorId}");
				}
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
			}
		}.toString();
		return sql;
	}
}

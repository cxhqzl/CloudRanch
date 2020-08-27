package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class PlaceSensorProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_placesensor");
				if(map.get("placeId") != null){
					SET("placeid = #{placeId}");
				}
				if(map.get("sensorId") != null){
					SET("sensorid = #{sensorId}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_placesensor");
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("sensorId") != null){
					WHERE("sensorid = #{sensorId}");
				}
			}
		}.toString();
		return sql;
	}
}

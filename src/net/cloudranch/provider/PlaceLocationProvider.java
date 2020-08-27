package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class PlaceLocationProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_placelocation");
				if(map.get("lng") != null){
					SET("lng = #{lng}");
				}
				if(map.get("lat") != null){
					SET("lat = #{lat}");
				}
				if(map.get("placeId") != null){
					SET("placeid = #{placeId}");
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
				FROM("t_placelocation");
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("number") != null){
					WHERE("number = #{number}");
				}
			}
		}.toString();
		sql += " ORDER BY number ";
		return sql;
	}
}

package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class OverallProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_overall");
				if(map.get("image") != null){
					SET("image = #{image}");
				}
				if(map.get("overallURL") != null){
					SET("overallURL = #{overallURL}");
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
				FROM("t_overall");
				if(map.get("id") != null){
					WHERE("id = #{id}");
				}
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
			}
		}.toString();
		return sql;
	}
}

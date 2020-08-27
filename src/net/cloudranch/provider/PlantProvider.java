package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class PlantProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_plant");
				if(map.get("cropName") != null){
					SET("cropname = #{cropName}");
				}
				if(map.get("iamge") != null){
					SET("iamge = #{iamge}");
				}
				if(map.get("placeId") != null){
					SET("placeid = #{placeId}");
				}
				WHERE("cropid = #{cropId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_plant");
				if(map.get("cropId") != null){
					WHERE("cropid = #{cropId}");
				}
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
			}
		}.toString();
		return sql;
	}
}

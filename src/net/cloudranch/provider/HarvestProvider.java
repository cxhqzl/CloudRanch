package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class HarvestProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_harvest");
				if(map.get("remarks") != null){
					SET("remarks = #{remarks}");
				}
				if(map.get("image") != null){
					SET("image = #{image}");
				}
				if(map.get("cropId") != null){
					SET("cropid = #{cropId}");
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
				FROM("t_harvest");
				if(map.get("cropId") != null){
					WHERE("cropid = #{cropId}");
				}
				if(map.get("createDate") != null){
					WHERE("createdate = #{createDate}");
				}
				if(map.get("id") != null){
					WHERE("id = #{id}");
				}
			}
		}.toString();
		return sql;
	}
}

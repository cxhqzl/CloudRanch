package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ForageProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_forage");
				if(map.get("forageName") != null){
					SET("foragename = #{forageName}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("produceDate") != null){
					SET("producedate = #{produceDate}");
				}
				WHERE("forageid = #{forageId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_forage");
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("forageId") != null){
					WHERE("forageid = #{forageId}");
				}
				if(map.get("key") != null){
					WHERE("foragename LIKE CONCAT ('%',#{key},'%') OR account LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("producedate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("producedate <= #{stopDate}");
				}
			}
		}.toString();
		sql += " ORDER BY account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
}

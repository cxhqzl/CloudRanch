package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class GrowInfoProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_growinfo");
				if(map.get("weight") != null){//ĞŞ¸ÄÃÜÂë
					SET("weight = #{weight}");
				}
				if(map.get("age") != null){//ĞŞ¸ÄÃÜÂë
					SET("age = #{age}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("createDate") != null){//ĞŞ¸ÄÃÜÂë
					SET("createdate = #{createDate}");
				}
				WHERE("growinfoid = #{growInfoId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_growinfo");
				if(map.get("sheepId") != null){
					WHERE("sheepid = #{sheepId}");
				}
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("key") != null) {
					WHERE("sheepid LIKE CONCAT ('%',#{key},'%') OR weight LIKE CONCAT ('%',#{key},'%') OR age LIKE CONCAT ('%',#{key},'%') OR account LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("createdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("createdate <= #{stopDate}");
				}
			}
		}.toString();
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null && (int) map.get("limit") != 0) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		if(map.get("paixu") != null) {
			sql += map.get("paixu");
		}
		return sql;
	}
}

package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class CheckInfoProvider {
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_checkinfo");
				if(map.get("checkInfoId") != null){
					WHERE("checkinfoid = #{checkInfoId}");
				}
				if(map.get("sheepId") != null){
					WHERE("sheepid = #{sheepId}");
				}
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("key") != null) {
					WHERE("sheepid LIKE CONCAT ('%',#{key},'%') OR account LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("checkdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("checkdate <= #{stopDate}");
				}
			}
		}.toString();
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_checkinfo");
				if(map.get("remarks") != null){
					SET("remarks=#{remarks}");
				}
				if(map.get("whether") != null){
					SET("whether=#{whether}");
				}
				if(map.get("account") != null){
					SET("account=#{account}");
				}
				if(map.get("checkDate") != null){
					SET("checkdate=#{checkDate}");
				}
				WHERE("checkinfoid = #{checkInfoId}");
			}
		}.toString();
		return sql;
	}
}

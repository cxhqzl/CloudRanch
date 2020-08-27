package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class VaccineProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_vaccine");
				if(map.get("vaccineName") != null){
					SET("vaccinename = #{vaccineName}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("vaccineBrand") != null){
					SET("vaccinebrand = #{vaccineBrand}");
				}
				if(map.get("produceDate") != null){
					SET("producedate = #{produceDate}");
				}
				WHERE("vaccineid = #{vaccineId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_vaccine");
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("vaccineId") != null){
					WHERE("vaccineid = #{vaccineId}");
				}
				if(map.get("key") != null){
					WHERE("vaccinename LIKE CONCAT ('%',#{key},'%') OR vaccinebrand LIKE CONCAT ('%',#{key},'%') OR account LIKE CONCAT ('%',#{key},'%')");
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

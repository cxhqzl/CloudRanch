package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class VaccineRecordProvider {
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_vaccinerecord");
				if(map.get("vaccineRecordId") != null){
					WHERE("vaccinerecordid = #{vaccineRecordId}");
				}
				if(map.get("sheepId") != null){
					WHERE("sheepid = #{sheepId}");
				}
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
			}
		}.toString();
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String selectJoin(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("t_vaccinerecord.*,t_vaccine.vaccinename,t_vaccine.vaccinebrand");
				FROM("t_vaccinerecord,t_vaccine");
				if(map.get("key") != null) {
					WHERE("t_vaccinerecord.sheepid LIKE CONCAT ('%',#{key},'%') OR t_vaccinerecord.account LIKE CONCAT ('%',#{key},'%') OR t_vaccine.vaccinename LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("t_vaccinerecord.recorddate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("t_vaccinerecord.recorddate <= #{stopDate}");
				}
				WHERE("t_vaccine.vaccineid = t_vaccinerecord.vaccineid");
			}
		}.toString();
		sql += " GROUP BY vaccinerecordid ";
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit} ";
		}
		return sql;
	}
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_vaccinerecord");
				if(map.get("recordDate") != null){
					SET("recorddate=#{recordDate}");
				}
				if(map.get("vaccineId") != null){
					SET("vaccineid=#{vaccineId}");
				}
				if(map.get("account") != null){
					SET("account=#{account}");
				}
				WHERE("vaccinerecordid = #{vaccineRecordId}");
			}
		}.toString();
		return sql;
	}
	
	
}

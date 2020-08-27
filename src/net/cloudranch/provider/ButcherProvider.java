package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ButcherProvider {
	public String select(Map<String,Object> map) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("t_butcher");
				if(map.get("butcherId") != null) {
					WHERE("butcherid = #{butcherId}");
				}
				if(map.get("account") != null) {
					WHERE("account = #{account}");
				}
				if(map.get("sheepId") != null) {
					WHERE("sheepid = #{sheepId}");
				}
				if(map.get("key") != null) {
					WHERE("sheepid LIKE CONCAT ('%',#{key},'%') OR account LIKE CONCAT ('%',#{key},'%') OR province LIKE CONCAT ('%',#{key},'%') OR city LIKE CONCAT ('%',#{key},'%') OR county LIKE CONCAT ('%',#{key},'%') OR location LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("butcherdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("butcherdate <= #{stopDate}");
				}
			}
		}.toString();
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String selectDate_Sheeps(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("count(*) as count,butcherdate as date");
				FROM("t_butcher");
				if(map.get("key") != null) {
					WHERE("t_sheep.sheepid LIKE CONCAT ('%',#{key},'%') OR t_sheep.sex LIKE CONCAT ('%',#{key},'%') OR t_sheep.account LIKE CONCAT ('%',#{key},'%') OR t_site.sitename LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("butcherdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("butcherdate <= #{stopDate}");
				}
			}
		}.toString();
		sql += " GROUP BY butcherdate ";
		return sql;
	}
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_butcher");
				if(map.get("remarks") != null){
					SET("remarks=#{remarks}");
				}
				if(map.get("butcherDate") != null){
					SET("butcherdate=#{butcherDate}");
				}
				if(map.get("butcherId") != null) {
					WHERE("butcherid = #{butcherId}");
				}
				if(map.get("sheepId") != null) {
					WHERE("sheepid = #{sheepId}");
				}
			}
		}.toString();
		return sql;
	}
}

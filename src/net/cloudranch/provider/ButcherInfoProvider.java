package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ButcherInfoProvider {
	public String select(Map<String,Object> map) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("t_butcherinfo");
				if(map.get("account") != null) {
					WHERE("account = #{account}");
				}
				if(map.get("unitId") != null) {
					WHERE("unitid = #{unitId}");
				}
				if(map.get("sheepId") != null){
					WHERE("unitid LIKE CONCAT ('%',#{sheepId},'%')");
				}
			}
		}.toString();
		sql += " ORDER BY unitid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String selectJoin(Map<String,Object> map) {
		String sql = new SQL() {
			{
				SELECT("t_butcherinfo.*,t_butcher.butcherdate");
				FROM("t_butcherinfo,t_butcher");
				if(map.get("key") != null) {
					WHERE("t_butcherinfo.unitid LIKE CONCAT ('%',#{key},'%') OR t_butcherinfo.account LIKE CONCAT ('%',#{key},'%') OR t_butcherinfo.transportid LIKE CONCAT ('%',#{key},'%') OR t_butcherinfo.company LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("t_butcher.butcherdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("t_butcher.butcherdate <= #{stopDate}");
				}
				WHERE("t_butcherinfo.unitid LIKE CONCAT ('%',t_butcher.sheepid,'%')");
			}
		}.toString();
		sql += " GROUP BY unitid ";
		sql += " ORDER BY unitid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_butcherinfo");
				if(map.get("unitName") != null){
					SET("unitname=#{unitName}");
				}
				WHERE("unitid = #{unitId}");
			}
		}.toString();
		return sql;
	}
}

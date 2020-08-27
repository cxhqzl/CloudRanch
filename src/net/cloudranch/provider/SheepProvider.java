package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SheepProvider {
	/**
	 * 多条件修改信息，必须带入主键作为修改条件
	 * @param map
	 * @return
	 */
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_sheep");
				if(map.get("sex") != null){
					SET("sex = #{sex}");
				}
				if(map.get("source") != null){
					SET("source = #{source}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("placeId") != null){
					SET("placeId = #{placeId}");
				}
				if(map.get("createDate") != null){
					SET("createdate = #{createDate}");
				}
				WHERE("sheepid = #{sheepId}");
			}
		}.toString();
		return sql;
	}
	/**
	 * 多添件查询
	 * @param map
	 * @return
	 */
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_sheep");
				if(map.get("sheepId") != null){
					WHERE("sheepid = #{sheepId}");
				}
				if(map.get("vagueSheepId") != null) {
					WHERE("sheepid LIKE CONCAT ('%',#{vagueSheepId},'%')");
				}
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("sex") != null){
					WHERE("sex = #{sex}");
				}
				if(map.get("startDate") != null){
					WHERE("createdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("createdate <= #{stopDate}");
				}
			}
		}.toString();
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += " LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	
	public String selectJoin(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("t_sheep.*,t_site.sitename");
				FROM("t_sheep,t_site,t_place");
				if(map.get("key") != null) {
					WHERE("t_sheep.sheepid LIKE CONCAT ('%',#{key},'%') OR t_sheep.sex LIKE CONCAT ('%',#{key},'%') OR t_sheep.account LIKE CONCAT ('%',#{key},'%') OR t_site.sitename LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("t_sheep.createdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("t_sheep.createdate <= #{stopDate}");
				}
				WHERE("t_place.placeid = t_sheep.placeid AND t_site.siteid = t_place.siteid");
			}
		}.toString();
		sql += " GROUP BY sheepid ";
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit} ";
		}
		return sql;
	}
	
	public String selectDate_Sheeps(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("count(*) as count,createdate as date");
				FROM("t_sheep");
				if(map.get("key") != null) {
					WHERE("t_sheep.sheepid LIKE CONCAT ('%',#{key},'%') OR t_sheep.sex LIKE CONCAT ('%',#{key},'%') OR t_sheep.account LIKE CONCAT ('%',#{key},'%') OR t_site.sitename LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("createdate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("createdate <= #{stopDate}");
				}
			}
		}.toString();
		sql += " GROUP BY createdate ";
		return sql;
	}
}

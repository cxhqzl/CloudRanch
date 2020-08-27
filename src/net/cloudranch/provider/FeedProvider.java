package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class FeedProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_feed");
				if(map.get("feedNumber") != null){
					SET("feednumber = #{feedNumber}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("forageId") != null){
					SET("forageid = #{forageId}");
				}
				if(map.get("feedDate") != null){
					SET("feeddate = #{feedDate}");
				}
				WHERE("feedid = #{feedId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_feed");
				if(map.get("feedId") != null){
					WHERE("feedid = #{feedId}");
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
		if(map.get("paixu") != null) {
			sql += map.get("paixu");
		}
		return sql;
	}
	
	public String selectJoin(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("t_feed.*,t_forage.foragename");
				FROM("t_feed,t_forage");
				if(map.get("key") != null) {
					WHERE("t_feed.sheepid LIKE CONCAT ('%',#{key},'%') OR t_forage.foragename LIKE CONCAT ('%',#{key},'%') OR t_feed.feednumber LIKE CONCAT ('%',#{key},'%') OR t_feed.account LIKE CONCAT ('%',#{key},'%')");
				}
				if(map.get("startDate") != null){
					WHERE("t_feed.feeddate >= #{startDate}");
				}
				if(map.get("stopDate") != null){
					WHERE("t_feed.feeddate <= #{stopDate}");
				}
				WHERE("t_forage.forageid = t_feed.forageid");
			}
		}.toString();
		sql += " GROUP BY feedid ";
		sql += " ORDER BY sheepid,account ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		if(map.get("paixu") != null) {
			sql += map.get("paixu");
		}
		return sql;
	}
}

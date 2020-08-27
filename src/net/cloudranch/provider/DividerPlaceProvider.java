package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class DividerPlaceProvider {
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_dividerPlace");
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("dividerId") != null){
					WHERE("dividerid = #{dividerId}");
				}
			}
		}.toString();
		sql += " GROUP BY createDate ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_dividerPlace");
				if(map.get("crop") != null){
					SET("crop = #{crop}");
				}
				if(map.get("square") != null){
					SET("square = #{square}");
				}
				if(map.get("placeId") != null){
					SET("placeid = #{placeId}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("createDate") != null){
					SET("createdate = #{createDate}");
				}
				WHERE("dividerid = #{dividerId}");
			}
		}.toString();
		return sql;
	}
}

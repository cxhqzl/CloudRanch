package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class PlaceProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_place");
				if(map.get("placeName") != null){
					SET("placename = #{placeName}");
				}
				if(map.get("square") != null){
					SET("square = #{square}");
				}
				if(map.get("siteId") != null){
					SET("siteid = #{siteId}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("fillColor") != null){
					SET("fillcolor = #{fillColor}");
				}
				if(map.get("fillOpacity") != null){
					SET("fillopacity = #{fillOpacity}");
				}
				if(map.get("strokeColor") != null){
					SET("strokecolor = #{strokeColor}");
				}
				if(map.get("strokeOpacity") != null){
					SET("strokeopacity = #{strokeOpacity}");
				}
				WHERE("placeid = #{placeId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_place");
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("siteId") != null){
					WHERE("siteid = #{siteId}");
				}
				if(map.get("type") != null){
					WHERE("type = #{type}");
				}
				if(map.get("account_no") != null){
					WHERE("account != #{account_no}");
				}
			}
		}.toString();
		return sql;
	}
}

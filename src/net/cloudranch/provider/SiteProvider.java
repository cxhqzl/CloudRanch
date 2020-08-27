package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SiteProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_site");
				if(map.get("siteName") != null){
					SET("sitename = #{siteName}");
				}
				if(map.get("crop") != null){
					SET("crop = #{crop}");
				}
				if(map.get("square") != null){
					SET("square = #{square}");
				}
				if(map.get("province") != null){
					SET("province = #{province}");
				}
				if(map.get("city") != null){
					SET("city = #{city}");
				}
				if(map.get("county") != null){
					SET("county = #{county}");
				}
				if(map.get("location") != null){
					SET("location = #{location}");
				}
				if(map.get("lng") != null){
					SET("lng = #{lng}");
				}
				if(map.get("lat") != null){
					SET("lat = #{lat}");
				}
				if(map.get("createDate") != null){
					SET("createDate = #{createDate}");
				}
				if(map.get("account") != null){
					SET("account = #{account}");
				}
				if(map.get("remarks") != null){
					SET("remarks = #{remarks}");
				}
				WHERE("siteid = #{siteId}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_site");
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("siteId") != null){
					WHERE("siteid = #{siteId}");
				}
				if(map.get("searchSites") != null){
					WHERE("sitename LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR crop LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR province LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR city LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR county LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR location LIKE CONCAT ('%',#{searchSites},'%')");
				}
			}
		}.toString();
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += " LIMIT #{beginIndex}, #{limit} ";
		}
		return sql;
	}
}

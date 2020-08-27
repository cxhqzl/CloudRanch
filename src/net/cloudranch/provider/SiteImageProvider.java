package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SiteImageProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_siteimage");
				if(map.get("image") != null){
					SET("image = #{image}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_siteimage");
				if(map.get("siteId") != null){
					WHERE("siteid = #{siteId}");
				}
				if(map.get("id") != null){
					WHERE("id = #{id}");
				}
			}
		}.toString();
		sql += " ORDER BY createDate ";
		return sql;
	}
}

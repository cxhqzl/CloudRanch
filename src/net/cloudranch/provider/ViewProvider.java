package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ViewProvider {
	public String selectSiteInfos(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("v_searchSiteInfo");
				if(map.get("siteId") != null){
					WHERE("siteid = #{siteId}");
				}
				if(map.get("searchSites") != null){
					WHERE("sitename LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR username LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR province LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR city LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR county LIKE CONCAT ('%',#{searchSites},'%') "
							+ "OR location LIKE CONCAT ('%',#{searchSites},'%')");
				}
			}
		}.toString();
		if(map.get("limit") != null) {
			sql += " LIMIT #{limit};  ";
		}
		return sql;
	}
	public String selectCrops(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("v_searchCrops");
				if(map.get("searchSites") != null){
					WHERE("crops LIKE CONCAT ('%',#{searchSites},'%') ");
				}
			}
		}.toString();
		return sql;
	}
}

package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class TransportCompanyProvider {
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_transportcompany");
				if(map.get("account") != null) {
					WHERE("account = #{account}");
				}
			}
		}.toString();
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_transportcompany");
				if(map.get("name") != null){
					SET("name = #{name}");
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
				WHERE("companyid = #{companyId}");
			}
		}.toString();
		return sql;
	}
}

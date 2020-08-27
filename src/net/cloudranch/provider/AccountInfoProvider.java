package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class AccountInfoProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_accountinfo");
				if(map.get("userName") != null){
					SET("username = #{userName}");
				}
				if(map.get("sex") != null){
					SET("sex = #{sex}");
				}
				if(map.get("age") != null){
					SET("age = #{age}");
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
				if(map.get("phone") != null){
					SET("phone = #{phone}");
				}
				WHERE("account = #{account}");
			}
		}.toString();
		return sql;
	}
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_accountinfo");
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
			}
		}.toString();
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += " LIMIT #{beginIndex}, #{limit}; ";
		}
		return sql;
	}
}

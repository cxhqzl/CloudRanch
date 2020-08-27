package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_account");
				if(map.get("password") != null){
					SET("password = #{password}");
				}
				if(map.get("role") != null){
					SET("role = #{role}");
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
				FROM("t_account");
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("password") != null){
					WHERE("password = #{password}");
				}
			}
		}.toString();
		return sql;
	}
}

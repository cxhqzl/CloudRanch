package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class LoginLogProvider {
	
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_loginlog");
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
				if(map.get("type") != null){
					WHERE("type = #{type}");
				}
			}
		}.toString();
		return sql;
	}
}

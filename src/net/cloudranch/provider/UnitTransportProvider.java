package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class UnitTransportProvider {
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_unittransport");
				if(map.get("sheepId") != null){
					WHERE("unitid LIKE CONCAT ('%',#{sheepId},'%')");
				}
				if(map.get("account") != null){
					WHERE("account = #{account}");
				}
			}
		}.toString();
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
}
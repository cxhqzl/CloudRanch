package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SoilProvider {
	public String select(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("t_soil");
				if(map.get("sensorId") != null){
					WHERE("sensorid = #{sensorId}");
				}
				if(map.get("createDate") != null){
					WHERE("createdate BETWEEN #{beginDate} AND #{endDate}");
				}
			}
		}.toString();
		sql += " GROUP BY createDate ";
		if(map.get("limit") != null && map.get("beginIndex") != null) {
			sql += "LIMIT #{beginIndex}, #{limit};";
		}
		return sql;
	}
	public String delete(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				DELETE_FROM("t_soil");
				if(map.get("sensorId") != null){
					WHERE("sensorid = #{sensorId}");
				}
				if(map.get("createDate") != null){
					WHERE("createdate BETWEEN #{beginDate} AND #{endDate}");
				}
			}
		}.toString();
		return sql;
	}
}

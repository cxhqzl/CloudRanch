package net.cloudranch.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class VidoProvider {
	public String update(Map<String,Object> map) {
		String sql =  new SQL(){
			{
				UPDATE("t_vido");
				if(map.get("vidoName") != null){
					SET("vidoname = #{vidoName}");
				}
				if(map.get("vidoUrl") != null){
					SET("vidourl = #{vidoUrl}");
				}
				if(map.get("vidoType") != null){
					SET("vidotype = #{vidoType}");
				}
				if(map.get("placeId") != null){
					SET("placeid = #{placeId}");
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
				FROM("t_vido");
				if(map.get("placeId") != null){
					WHERE("placeid = #{placeId}");
				}
				if(map.get("id") != null){
					WHERE("id = #{id}");
				}
			}
		}.toString();
		return sql;
	}
}

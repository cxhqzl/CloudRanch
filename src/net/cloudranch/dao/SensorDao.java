package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Sensor;
import net.cloudranch.provider.SensorProvider;

public interface SensorDao {
	
	@Insert("INSERT INTO "
			+ "t_sensor(type,sensorname,mac,intervals,isreport,status,battery,createDate,placeid,picture,icon) "
			+ "values(#{type},#{sensorName},#{mac},#{interval},#{isReport},#{status},#{battery},#{createDate},#{placeId},#{picture},#{icon})")
	public int insert(Sensor sensor);
	
	@Delete("DELETE FROM t_sensor WHERE sensorid = #{sensorId}")
	public int delete(int sensorId);
	
	@UpdateProvider(type=SensorProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=SensorProvider.class,method="select")
	public List<Sensor> select(Map<String,Object> map);
	
}

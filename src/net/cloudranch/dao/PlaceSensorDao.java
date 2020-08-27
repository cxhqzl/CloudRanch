package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.PlaceSensor;
import net.cloudranch.provider.PlaceSensorProvider;

public interface PlaceSensorDao {
	@Insert("INSERT INTO "
			+ "t_placesensor(placeid,sensorid,sensortype) "
			+ "VALUES(#{placeId},#{sensorId},#{sensorType})")
	public int insert(PlaceSensor placeSensor);
	
	@Delete("DELETE FROM t_placesensor WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=PlaceSensorProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=PlaceSensorProvider.class,method="select")
	public List<PlaceSensor> select(Map<String,Object> map);
}

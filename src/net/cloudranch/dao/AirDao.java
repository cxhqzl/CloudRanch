package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Air;
import net.cloudranch.provider.AirProvider;

public interface AirDao {
	@Insert("INSERT INTO "
			+ "t_air(temp,hum,alarmtype,createdate,sensorid) "
			+ "VALUES(#{temp},#{hum},#{alarmType},#{createDate},#{sensorId})")
	public int insert(Air air);
	
	@DeleteProvider(type=AirProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=AirProvider.class,method="select")
	public List<Air> select(Map<String,Object> map);
}

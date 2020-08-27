package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Soil;
import net.cloudranch.provider.SoilProvider;

public interface SoilDao {
	@Insert("INSERT INTO "
			+ "t_soil(sensorid,temp,hum,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{temp},#{hum},#{alarmType},#{createDate})")
	public int insert(Soil soil);
	
	@DeleteProvider(type=SoilProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=SoilProvider.class,method="select")
	public List<Soil> select(Map<String,Object> map);
}

package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Radiation;
import net.cloudranch.provider.RadiationProvider;

public interface RadiationDao {
	@Insert("INSERT INTO "
			+ "t_radiation(sensorid,intensity,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{intensity},#{alarmType},#{createDate})")
	public int insert(Radiation radiation);
	
	@DeleteProvider(type=RadiationProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=RadiationProvider.class,method="select")
	public List<Radiation> select(Map<String,Object> map);
}

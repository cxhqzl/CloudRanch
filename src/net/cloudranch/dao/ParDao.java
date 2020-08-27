package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Par;
import net.cloudranch.provider.ParProvider;

public interface ParDao {
	@Insert("INSERT INTO "
			+ "t_par(sensorid,intensity,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{intensity},#{alarmType},#{createDate})")
	public int insert(Par par);
	
	@DeleteProvider(type=ParProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=ParProvider.class,method="select")
	public List<Par> select(Map<String,Object> map);
}

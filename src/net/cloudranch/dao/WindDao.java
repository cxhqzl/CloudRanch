package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Wind;
import net.cloudranch.provider.WindProvider;

public interface WindDao {
	@Insert("INSERT INTO "
			+ "t_wind(sensorid,speed,direction,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{speed},#{direction},#{alarmType},#{createDate})")
	public int insert(Wind wind);
	
	@DeleteProvider(type=WindProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=WindProvider.class,method="select")
	public List<Wind> select(Map<String,Object> map);
}

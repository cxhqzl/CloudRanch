package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.RainFall;
import net.cloudranch.provider.RainFallProvider;

public interface RainFallDao {
	@Insert("INSERT INTO "
			+ "t_rainfall(rainfall,alarmtype,createdate,sensorid) "
			+ "VALUES(#{rainFall},#{alarmType},#{createDate},#{sensorId})")
	public int insert(RainFall rainFall);
	
	@DeleteProvider(type=RainFallProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=RainFallProvider.class,method="select")
	public List<RainFall> select(Map<String,Object> map);
}

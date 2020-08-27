package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Co2;
import net.cloudranch.provider.Co2Provider;

public interface Co2Dao {
	@Insert("INSERT INTO "
			+ "t_co2(sensorid,concen,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{concen},#{alarmType},#{createDate})")
	public int insert(Co2 co2);
	
	@DeleteProvider(type=Co2Provider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=Co2Provider.class,method="select")
	public List<Co2> select(Map<String,Object> map);
}

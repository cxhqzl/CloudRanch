package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Nh3;
import net.cloudranch.provider.Nh3Provider;

public interface Nh3Dao {
	@Insert("INSERT INTO "
			+ "t_nh3(sensorid,concen,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{concen},#{alarmType},#{createDate})")
	public int insert(Nh3 Nh3);
	
	@DeleteProvider(type=Nh3Provider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=Nh3Provider.class,method="select")
	public List<Nh3> select(Map<String,Object> map);
}

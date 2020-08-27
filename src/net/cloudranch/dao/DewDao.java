package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.Dew;
import net.cloudranch.provider.DewProvider;

public interface DewDao {
	@Insert("INSERT INTO "
			+ "t_dew(temp,alarmtype,createdate,sensorid) "
			+ "VALUES(#{temp},#{alarmType},#{createDate},#{sensorId})")
	public int insert(Dew dew);
	
	@DeleteProvider(type=DewProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=DewProvider.class,method="select")
	public List<Dew> select(Map<String,Object> map);
}

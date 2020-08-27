package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.H2s;
import net.cloudranch.provider.H2sProvider;

public interface H2sDao {
	@Insert("INSERT INTO "
			+ "t_h2s(sensorid,concen,alarmtype,createdate) "
			+ "VALUES(#{sensorId},#{concen},#{alarmType},#{createDate})")
	public int insert(H2s h2s);
	
	@DeleteProvider(type=H2sProvider.class,method="delete")
	public int delete(Map<String,Object> map);
	
	@SelectProvider(type=H2sProvider.class,method="select")
	public List<H2s> select(Map<String,Object> map);
}

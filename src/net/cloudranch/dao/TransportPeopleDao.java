package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.TransportPeople;
import net.cloudranch.provider.TransportPeopleProvider;

public interface TransportPeopleDao {
	@Insert("INSERT INTO "
			+ "t_transportpeople(name,phone,carid,province,city,county,location,lng,lat,companyid,account) "
			+ "VALUES(#{name},#{phone},#{carId},#{province},#{city},#{county},#{location},#{lng},#{lat},#{companyId},#{account})")
	public int insert(TransportPeople transportPeople);
	
	@Delete("DELETE FROM t_transportpeople WHERE peopleid = #{peopleId}")
	public int delete(int peopleId);
	
	@UpdateProvider(type=TransportPeopleProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=TransportPeopleProvider.class,method="select")
	public List<TransportPeople> select(Map<String,Object> map);
}

package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.UnitTransport;
import net.cloudranch.provider.UnitTransportProvider;

public interface UnitTransportDao {
	
	@Insert("INSERT INTO "
			+ "t_unittransport(startdate,stopdate,province,city,county,location,lng,lat,unitid,peopleid,account) "
			+ "VALUES(#{startDate},#{stopDate},#{province},#{city},#{county},#{location},#{lng},#{lat},#{unitId},#{peopleId},#{account})")
	public int insert(UnitTransport ut);
	
	@SelectProvider(type=UnitTransportProvider.class,method="select")
	public List<UnitTransport> select(Map<String,Object> map);
}

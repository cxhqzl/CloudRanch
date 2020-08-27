package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import net.cloudranch.domain.SheepTransport;
import net.cloudranch.provider.SheepTransportProvider;

public interface SheepTransportDao {
	
	@Insert("INSERT INTO "
			+ "t_sheeptransport(startdate,stopdate,province,city,county,location,lng,lat,sheepid,peopleid,account) "
			+ "VALUES(#{startDate},#{stopDate},#{province},#{city},#{county},#{location},#{lng},#{lat},#{sheepId},#{peopleId},#{account})")
	public int insert(SheepTransport st);
	
	@SelectProvider(type=SheepTransportProvider.class,method="select")
	public List<SheepTransport> select(Map<String,Object> map);
}

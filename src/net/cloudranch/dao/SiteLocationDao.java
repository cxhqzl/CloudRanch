package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.SiteLocation;
import net.cloudranch.provider.SiteLocationProvider;

public interface SiteLocationDao {

	@Insert("INSERT INTO "
			+ "t_sitelocation(number,locationname,lng,lat,siteid) "
			+ "values(#{number},#{locationName},#{lng},#{lat},#{siteId})")
	public int insert(SiteLocation siteLocation);
	
	@Delete("DELETE FROM t_sitelocation WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=SiteLocationProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=SiteLocationProvider.class,method="select")
	public List<SiteLocation> select(Map<String,Object> map);
}

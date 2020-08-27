package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.PlaceLocation;
import net.cloudranch.provider.PlaceLocationProvider;

public interface PlaceLocationDao {

	@Insert("INSERT INTO "
			+ "t_placelocation(number,locationname,lng,lat,placeid) "
			+ "values(#{number},#{locationName},#{lng},#{lat},#{placeId})")
	public int insert(PlaceLocation placeLocation);
	
	@Delete("DELETE FROM t_placelocation WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=PlaceLocationProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=PlaceLocationProvider.class,method="select")
	public List<PlaceLocation> select(Map<String,Object> map);
}

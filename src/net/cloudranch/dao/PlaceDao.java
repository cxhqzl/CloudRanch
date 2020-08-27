package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Place;
import net.cloudranch.provider.PlaceProvider;

public interface PlaceDao {
	
	@Insert("INSERT INTO "
			+ "t_place(placename,square,createdate,siteid,vidotype,vidourl,crop,type,account,strokecolor,fillcolor,strokeopacity,fillopacity) "
			+ "values(#{placeName},#{square},#{createDate},#{siteId},#{vidoType},#{vidoUrl},#{crop},#{type},#{account},#{strokeColor},#{fillColor},#{strokeOpacity},#{fillOpacity})")
	public int insert(Place place);
	
	@Delete("DELETE FROM t_place WHERE placeid = #{placeId}")
	public int delete(int placeId);
	
	@UpdateProvider(type=PlaceProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=PlaceProvider.class,method="select")
	public List<Place> select(Map<String,Object> map);
	
}

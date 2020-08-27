package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Plant;
import net.cloudranch.provider.PlantProvider;

public interface PlantDao {
	@Insert("INSERT INTO "
			+ "t_plant(cropid,cropname,createdate,image,placeid) "
			+ "VALUES(#{cropId},#{cropName},#{createDate},#{image},#{placeId})")
	public int insert(Plant plant);
	
	@Delete("DELETE FROM t_plant WHERE cropid = #{cropId}")
	public int delete(String cropId);
	
	@UpdateProvider(type=PlantProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=PlantProvider.class,method="select")
	public List<Plant> select(Map<String,Object> map);
}

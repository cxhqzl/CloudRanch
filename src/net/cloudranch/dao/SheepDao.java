package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Sheep;
import net.cloudranch.provider.SheepProvider;

public interface SheepDao {
	@Insert("INSERT INTO "
			+ "t_sheep(sheepid,sex,source,createdate,placeid,fatherid,motherid,image,account) "
			+ "VALUES(#{sheepId},#{sex},#{source},#{createDate},#{placeId},#{fatherId},#{motherId},#{image},#{account})")
	public int insert(Sheep sheep);
	
	@Delete("DELETE FROM t_sheep WHERE sheepid = #{sheepId}")
	public int delete(String sheepId);
	
	@UpdateProvider(type=SheepProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=SheepProvider.class,method="select")
	public List<Sheep> select(Map<String,Object> map);
	
}

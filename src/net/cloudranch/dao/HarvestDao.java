package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Harvest;
import net.cloudranch.provider.HarvestProvider;

public interface HarvestDao {
	@Insert("INSERT INTO "
			+ "t_harvest(remarks,createdate,image,cropid) "
			+ "VALUES(#{remarks},#{createDate},#{image},#{cropId})")
	public int insert(Harvest harvest);
	
	@Delete("DELETE FROM t_harvest WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=HarvestProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=HarvestProvider.class,method="select")
	public List<Harvest> select(Map<String,Object> map);
}

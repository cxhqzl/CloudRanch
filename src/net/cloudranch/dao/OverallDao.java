package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Overall;
import net.cloudranch.provider.OverallProvider;

public interface OverallDao {
	@Insert("INSERT INTO "
			+ "t_overall(image,overallURL,placeid) "
			+ "VALUES(#{image},#{overallURL},#{placeId})")
	public int insert(Overall overall);
	
	@Delete("DELETE FROM t_overall WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=OverallProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=OverallProvider.class,method="select")
	public List<Overall> select(Map<String,Object> map);
}

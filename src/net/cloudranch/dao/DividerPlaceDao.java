package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.DividerPlace;
import net.cloudranch.provider.DividerPlaceProvider;

public interface DividerPlaceDao {
	
	@Insert("INSERT INTO "
			+ "t_dividerPlace(crop,square,placeid,account,createdate) "
			+ "values(#{crop},#{square},#{placeId},#{account},#{createDate})")
	public int insert(DividerPlace DividerPlace);
	
	@Delete("DELETE FROM t_dividerPlace WHERE dividerId = #{dividerId}")
	public int delete(int dividerId);
	
	@UpdateProvider(type=DividerPlaceProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=DividerPlaceProvider.class,method="select")
	public List<DividerPlace> select(Map<String,Object> map);
	
}

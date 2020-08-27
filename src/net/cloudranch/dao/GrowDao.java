package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Grow;
import net.cloudranch.provider.GrowProvider;

public interface GrowDao {
	@Insert("INSERT INTO "
			+ "t_grow(remarks,createdate,image,cropid) "
			+ "VALUES(#{remarks},#{createDate},#{image},#{cropId})")
	public int insert(Grow grow);
	
	@Delete("DELETE FROM t_grow WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=GrowProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=GrowProvider.class,method="select")
	public List<Grow> select(Map<String,Object> map);
}

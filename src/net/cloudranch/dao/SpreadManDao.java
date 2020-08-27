package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.SpreadMan;
import net.cloudranch.provider.SpreadManProvider;

public interface SpreadManDao {
	@Insert("INSERT INTO "
			+ "t_spreadman(remarks,createdate,image,cropid) "
			+ "VALUES(#{remarks},#{createDate},#{image},#{cropId})")
	public int insert(SpreadMan spreadMan);
	
	@Delete("DELETE FROM t_spreadman WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=SpreadManProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=SpreadManProvider.class,method="select")
	public List<SpreadMan> select(Map<String,Object> map);
}

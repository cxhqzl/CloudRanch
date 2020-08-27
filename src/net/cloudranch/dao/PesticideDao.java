package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Pesticide;
import net.cloudranch.provider.PesticideProvider;

public interface PesticideDao {
	@Insert("INSERT INTO "
			+ "t_pesticide(remarks,createdate,image,cropid) "
			+ "VALUES(#{remarks},#{createDate},#{image},#{cropId})")
	public int insert(Pesticide pesticide);
	
	@Delete("DELETE FROM t_pesticide WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=PesticideProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=PesticideProvider.class,method="select")
	public List<Pesticide> select(Map<String,Object> map);
}

package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Irrigate;
import net.cloudranch.provider.IrrigateProvider;

public interface IrrigateDao {
	@Insert("INSERT INTO "
			+ "t_irrigate(remarks,createdate,image,cropid) "
			+ "VALUES(#{remarks},#{createDate},#{image},#{cropId})")
	public int insert(Irrigate irrigate);
	
	@Delete("DELETE FROM t_irrigate WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=IrrigateProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=IrrigateProvider.class,method="select")
	public List<Irrigate> select(Map<String,Object> map);
}
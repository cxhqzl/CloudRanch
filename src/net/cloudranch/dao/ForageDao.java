package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Forage;
import net.cloudranch.provider.ForageProvider;

public interface ForageDao {
	@Insert("INSERT INTO "
			+ "t_forage(foragename,producedate,account) "
			+ "VALUES(#{forageName},#{produceDate},#{account})")
	public int insert(Forage forage);
	
	@Delete("DELETE FROM t_forage WHERE forageid = #{forageId}")
	public int delete(int forageId);
	
	@UpdateProvider(type=ForageProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=ForageProvider.class,method="select")
	public List<Forage> select(Map<String,Object> map);
}

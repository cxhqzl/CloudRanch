package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Butcher;
import net.cloudranch.provider.ButcherProvider;


public interface ButcherDao {
	@Insert("INSERT INTO "
			+ "t_butcher(butcherid,butcherdate,image,province,city,county,location,lng,lat,sheepid,account) "
			+ "VALUES(#{butcherId},#{butcherDate},#{image},#{province},#{city},#{county},#{location},#{lng},#{lat},#{sheepId},#{account})")
	public int insert(Butcher butcher);
	
	@Delete("DELETE FROM t_butcher WHERE butcherid = #{butcherId}")
	public int delete(int butcherId);
	
	@UpdateProvider(method = "update", type = ButcherProvider.class)
	public int update(Map<String,Object> map);
	
	@SelectProvider(method="select",type=ButcherProvider.class)
	public List<Butcher> select(Map<String,Object> map);
}

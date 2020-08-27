package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.GrowInfo;
import net.cloudranch.provider.GrowInfoProvider;

public interface GrowInfoDao {
	@Insert("INSERT INTO "
			+ "t_growinfo(createdate,weight,image,age,sheepid,account) "
			+ "VALUES(#{createDate},#{weight},#{image},#{age},#{sheepId},#{account})")
	public int insert(GrowInfo growInfo);
	
	@Delete("DELETE FROM t_growinfo WHERE growinfoid = #{growInfoId}")
	public int delete(int growInfoId);
	
	@UpdateProvider(type=GrowInfoProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=GrowInfoProvider.class,method="select")
	public List<GrowInfo> select(Map<String,Object> map);
}

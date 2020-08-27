package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.CheckInfo;
import net.cloudranch.provider.CheckInfoProvider;

public interface CheckInfoDao {
	@Insert("INSERT INTO "
			+ "t_checkinfo(checkinfoid,checkdate,whether,remarks,sheepid,account) "
			+ "VALUES(#{checkInfoId},#{checkDate},#{whether},#{remarks},#{sheepId},#{account})")
	public int insert(CheckInfo checkInfo);
	
	@Delete("DELETE FROM t_checkinfo WHERE checkinfoid = #{checkInfoId}")
	public int delete(int checkInfoId);
	
	@UpdateProvider(method = "update", type = CheckInfoProvider.class)
	public int update(Map<String,Object> map);
	
	@SelectProvider(method="select",type=CheckInfoProvider.class)
	public List<CheckInfo> select(Map<String,Object> map);
}

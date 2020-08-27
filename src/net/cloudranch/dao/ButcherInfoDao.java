package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.ButcherInfo;
import net.cloudranch.provider.ButcherInfoProvider;

public interface ButcherInfoDao {
	@Insert("INSERT INTO "
			+ "t_butcherinfo(unitid,transportid,company,account) "
			+ "VALUES(#{unitId},#{transportId},#{company},#{account})")
	public int insert(ButcherInfo butcherInfo);
	
	@Delete("DELETE FROM t_butcherinfo WHERE unitid = #{unitId}")
	public int delete(String unitId);
	
	@UpdateProvider(method = "update", type = ButcherInfoProvider.class)
	public int update(Map<String,Object> map);
	
	@SelectProvider(method="select",type=ButcherInfoProvider.class)
	public List<ButcherInfo> select(Map<String,Object> map);
}

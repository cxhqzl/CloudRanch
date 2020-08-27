package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.VaccineRecord;
import net.cloudranch.provider.VaccineRecordProvider;

public interface VaccineRecordDao {
	@Insert("INSERT INTO "
			+ "t_vaccinerecord(vaccinerecordid,recorddate,vaccineid,sheepid,account) "
			+ "VALUES(#{vaccineRecordId},#{recordDate},#{vaccineId},#{sheepId},#{account})")
	public int insert(VaccineRecord vaccineRecord);
	
	@Delete("DELETE FROM t_vaccinerecord WHERE vaccinerecordid = #{vaccineRecordId}")
	public int delete(int vaccineRecordId);
	
	@UpdateProvider(method = "update", type = VaccineRecordProvider.class)
	public int update(Map<String,Object> map);
	
	@SelectProvider(method="select",type=VaccineRecordProvider.class)
	public List<VaccineRecord> select(Map<String,Object> map);
}

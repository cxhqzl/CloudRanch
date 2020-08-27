package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Vaccine;
import net.cloudranch.provider.VaccineProvider;

public interface VaccineDao {
	@Insert("INSERT INTO "
			+ "t_vaccine(vaccinename,producedate,vaccinebrand,account) "
			+ "VALUES(#{vaccineName},#{produceDate},#{vaccineBrand},#{account})")
	public int insert(Vaccine vaccine);
	
	@Delete("DELETE FROM t_vaccine WHERE vaccineid = #{vaccineId}")
	public int delete(int vaccineId);
	
	@UpdateProvider(type=VaccineProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=VaccineProvider.class,method="select")
	public List<Vaccine> select(Map<String,Object> map);
}

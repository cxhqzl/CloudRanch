package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.TransportCompany;
import net.cloudranch.provider.TransportCompanyProvider;

public interface TransportCompanyDao {
	@Insert("INSERT INTO "
			+ "t_transportcompany(name,province,city,county,location,lng,lat,account) "
			+ "VALUES(#{name},#{province},#{city},#{county},#{location},#{lng},#{lat},#{account})")
	public int insert(TransportCompany transportCompany);
	
	@Delete("DELETE FROM t_transportcompany WHERE companyid = #{companyId}")
	public int delete(int companyId);
	
	@UpdateProvider(type=TransportCompanyProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=TransportCompanyProvider.class,method="select")
	public List<TransportCompany> select(Map<String,Object> map);
}

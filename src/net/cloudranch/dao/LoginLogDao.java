package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import net.cloudranch.domain.LoginLog;
import net.cloudranch.provider.LoginLogProvider;

public interface LoginLogDao {
	@Insert("INSERT INTO "
			+ "t_loginlog(account,address,ip,logindate,type) "
			+ "VALUES(#{account},#{address},#{ip},#{loginDate},#{type})")
	public int insert(LoginLog loginLog);
	
	@Update("UPDATE t_loginlog "
			+ "SET address=#{address},ip=#{ip},logindate=#{loginDate} "
			+ "WHERE account=#{account} AND type=#{type}")
	public int update(LoginLog loginLog);
	
	@SelectProvider(type=LoginLogProvider.class,method="select")
	public List<LoginLog> select(Map<String,Object> map);
}

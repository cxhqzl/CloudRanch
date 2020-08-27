package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Account;
import net.cloudranch.provider.AccountProvider;

public interface AccountDao {
	@Insert("INSERT INTO "
			+ "t_account(account,password,role) "
			+ "VALUES(#{account},#{password},#{role})")
	public int insert(Account account);
	
	@Delete("DELETE FROM t_account WHERE account = #{account}")
	public int delete(String account);
	
	@UpdateProvider(type=AccountProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=AccountProvider.class,method="select")
	public List<Account> select(Map<String,Object> map);
}

package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.AccountInfo;
import net.cloudranch.provider.AccountInfoProvider;

public interface AccountInfoDao {
	@Insert("INSERT INTO "
			+ "t_accountinfo "
			+ "VALUES(#{account},#{userName},#{image},#{sex},#{age},#{province},#{city},#{county},#{location},#{lng},#{lat},#{phone},#{email},#{createDate})")
	public int insert(AccountInfo accountInfo);
	
	@Delete("DELETE FROM t_accountinfo WHERE account = #{account}")
	public int delete(String account);
	
	@UpdateProvider(type=AccountInfoProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=AccountInfoProvider.class,method="select")
	public List<AccountInfo> select(Map<String,Object> map);
}

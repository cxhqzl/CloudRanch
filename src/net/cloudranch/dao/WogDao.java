package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Wog;
import net.cloudranch.provider.WogProvider;

public interface WogDao {
	@Insert("INSERT INTO "
			+ "t_wog(remarks,createdate,image,cropid) "
			+ "VALUES(#{remarks},#{createDate},#{image},#{cropId})")
	public int insert(Wog wog);
	
	@Delete("DELETE FROM t_wog WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=WogProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=WogProvider.class,method="select")
	public List<Wog> select(Map<String,Object> map);
}

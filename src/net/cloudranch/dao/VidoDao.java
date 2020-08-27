package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Vido;
import net.cloudranch.provider.VidoProvider;

public interface VidoDao {
	@Insert("INSERT INTO "
			+ "t_vido(vidoname,vidourl,vidotype,placeid) "
			+ "VALUES(#{vidoName},#{vidoUrl},#{vidoType},#{placeId})")
	public int insert(Vido vido);
	
	@Delete("DELETE FROM t_vido WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=VidoProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=VidoProvider.class,method="select")
	public List<Vido> select(Map<String,Object> map);
}

package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.SiteImage;
import net.cloudranch.provider.SiteImageProvider;

public interface SiteImageDao {

	@Insert("INSERT INTO "
			+ "t_siteimage(image,createdate,siteid) "
			+ "values(#{image},#{createDate},#{siteId})")
	public int insert(SiteImage siteImage);
	
	@Delete("DELETE FROM t_siteimage WHERE id = #{id}")
	public int delete(int id);
	
	@UpdateProvider(type=SiteImageProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=SiteImageProvider.class,method="select")
	public List<SiteImage> select(Map<String,Object> map);
}

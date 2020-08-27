package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Site;
import net.cloudranch.provider.SiteProvider;

public interface SiteDao {
	
	@Insert("INSERT INTO "
			+ "t_site(sitename,square,province,city,county,location,lng,lat,createdate,account,remarks) "
			+ "values(#{siteName},#{square},#{province},#{city},#{county},#{location},#{lng},#{lat},#{createDate},#{account},#{remarks})")
	public int insert(Site site);
	
	@Delete("DELETE FROM t_site WHERE siteid = #{siteId}")
	public int delete(int siteId);
	
	@UpdateProvider(type=SiteProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=SiteProvider.class,method="select")
	public List<Site> select(Map<String,Object> map);
	
}

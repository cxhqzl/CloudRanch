package net.cloudranch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.cloudranch.domain.Feed;
import net.cloudranch.provider.FeedProvider;

public interface FeedDao {
	@Insert("INSERT INTO "
			+ "t_feed(feednumber,feeddate,forageid,sheepid,account) "
			+ "VALUES(#{feedNumber},#{feedDate},#{forageId},#{sheepId},#{account})")
	public int insert(Feed feed);
	
	@Delete("DELETE FROM t_feed WHERE feedid = #{feedId}")
	public int delete(int feedId);
	
	@UpdateProvider(type=FeedProvider.class,method="update")
	public int update(Map<String,Object> map);
	
	@SelectProvider(type=FeedProvider.class,method="select")
	public List<Feed> select(Map<String,Object> map);
}

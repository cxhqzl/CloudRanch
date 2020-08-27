package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Feed;

public interface FeedService {
	/**添加数据*/
	public boolean add(Feed feed);
	
	/**删除数据*/
	public boolean del(int feedId);
	
	/**修改数据*/
	public boolean modified(Map<String,Object> map);
	
	/**查询数据*/
	public List<Feed> query(Map<String,Object> map);
	
	/**查询记录总数*/
	public int queryCount(Map<String,Object> map);
}

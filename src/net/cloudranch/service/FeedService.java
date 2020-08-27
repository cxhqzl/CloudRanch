package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Feed;

public interface FeedService {
	/**�������*/
	public boolean add(Feed feed);
	
	/**ɾ������*/
	public boolean del(int feedId);
	
	/**�޸�����*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ����*/
	public List<Feed> query(Map<String,Object> map);
	
	/**��ѯ��¼����*/
	public int queryCount(Map<String,Object> map);
}

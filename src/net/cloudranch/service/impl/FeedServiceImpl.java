package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.FeedDao;
import net.cloudranch.domain.Feed;
import net.cloudranch.service.FeedService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("feedService")
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedDao feedDao;
	
	@Override
	public boolean add(Feed feed) {
		int res = feedDao.insert(feed);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean del(int feedId) {
		int res = feedDao.delete(feedId);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modified(Map<String, Object> map) {
		int res = feedDao.update(map);
		if(res <= 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Feed> query(Map<String, Object> map) {
		return feedDao.select(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return feedDao.select(map).size();
	}
}

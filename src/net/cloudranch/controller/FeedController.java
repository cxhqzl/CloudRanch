package net.cloudranch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cloudranch.domain.Feed;
import net.cloudranch.service.FeedService;
import net.cloudranch.service.ForageService;
import net.cloudranch.utils.BasicUtils;

@Controller
public class FeedController {
	
	@Autowired
	@Qualifier("feedService")
	private FeedService feedService;
	@Autowired
	@Qualifier("forageService")
	private ForageService forageService1;
	/**
	 * 添加饲养记录
	 * @param feedNumber
	 * @param forageId
	 * @param sheepId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addFeed")
	@ResponseBody
	boolean add(@RequestParam("feedNumber") String feedNumber,
			@RequestParam("forageId") String forageId,
			@RequestParam("sheepId") String sheepId,
			@RequestParam("account") String account) {
		Feed feed = new Feed(0,Double.parseDouble(feedNumber),BasicUtils.getDate(),Integer.parseInt(forageId),sheepId,account);
		return feedService.add(feed);
	}
	/**
	 * 查询饲养记录
	 * @param request
	 * @param sheepId
	 * @param account
	 * @param pageNumber
	 * @param limit
	 */
	@RequestMapping(value="/queryFeed")
	void query(HttpServletRequest request,
			@RequestParam("sheepId") String sheepId,
			@RequestParam("account") String account,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("account", account);
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<Feed> feeds = feedService.query(map);
		for(Feed f : feeds) {
			map.put("forageId", f.getForageId());
			f.setForageName(forageService1.getForageName(map));
		}
		request.getSession().setAttribute("feeds", feeds);
		map.remove("limit");
		request.getSession().setAttribute("size", feedService.queryCount(map));
		request.getSession().setAttribute("pageNumber", pageNumber);
	}
	/**
	 * 删除
	 * @param feedId
	 * @return
	 */
	@RequestMapping(value="/delFeed")
	@ResponseBody
	boolean del(@RequestParam("feedId") String feedId) {
		return feedService.del(Integer.parseInt(feedId));
	}
	/**
	 * 修改时间，饲料，喂养量
	 * @param feedId
	 * @param feedNumber
	 * @param forageId
	 * @param createDate
	 * @return
	 */
	@RequestMapping(value="/modifiFeedDateAndNumberAndForage")
	@ResponseBody
	boolean modifiFeedDateAndNumberAndForage(@RequestParam("feedId") String feedId,
											@RequestParam("feedNumber") String feedNumber,
											@RequestParam("forageId") String forageId,
											@RequestParam("createDate") String createDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("feedId", Integer.parseInt(feedId));
		map.put("feedNumber", Double.parseDouble(feedNumber));
		map.put("forageId", Integer.parseInt(forageId));
		map.put("feedDate", createDate);
		return feedService.modified(map);
	}
	@RequestMapping(value="/modifiFeed")
	@ResponseBody
	boolean modifiFeed(@RequestParam("feedId") String feedId,
							@RequestParam("feedNumber") String feedNumber,
							@RequestParam("forageId") String forageId,
							@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("feedId", Integer.parseInt(feedId));
		map.put("feedNumber", Double.parseDouble(feedNumber));
		map.put("forageId", Integer.parseInt(forageId));
		map.put("account", account);
		return feedService.modified(map);
	}
}

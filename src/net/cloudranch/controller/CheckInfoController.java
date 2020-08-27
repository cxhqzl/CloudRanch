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

import net.cloudranch.domain.CheckInfo;
import net.cloudranch.service.CheckInfoService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class CheckInfoController {
	@Autowired
	@Qualifier("checkInfoService")
	private CheckInfoService checkInfoService;
	
	@RequestMapping(value="/addCheckInfo")
	@ResponseBody
	boolean addCheckInfo(@RequestParam("sheepId") String sheepId,
			@RequestParam("whether") String whether,
			@RequestParam("remarks") String remarks,
			@RequestParam("account") String account) {
		CheckInfo checkInfo = new CheckInfo(0, BasicUtils.getDate(), Integer.parseInt(whether), remarks, sheepId, account);
		boolean flag = checkInfoService.add(checkInfo);
		return flag;
	}
	/**
	 * 查询检疫记录
	 * @param request
	 * @param sheepId
	 * @param account
	 * @param pageNumber
	 * @param limit
	 */
	@RequestMapping(value="/queryCheckInfo")
	void queryCheckInfo(HttpServletRequest request,
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
		List<CheckInfo> checkInfos = checkInfoService.queryBySheepIdAndAccount(map);
		request.getSession().setAttribute("checkInfos", checkInfos);
		map.remove("limit");
		request.getSession().setAttribute("size", checkInfoService.queryCount(map));
		request.getSession().setAttribute("pageNumber", pageNumber);
	}
	/**
	 * 删除
	 * @param checkInfoId
	 * @return
	 */
	@RequestMapping(value="/delCheckInfo")
	@ResponseBody
	boolean delCheckInfo(@RequestParam("checkInfoId") String checkInfoId) {
		return checkInfoService.del(Integer.parseInt(checkInfoId));
	}
	
	@RequestMapping(value="/modifiCheckInfoDateAndWhetherAndRemark")
	@ResponseBody
	boolean modifiCheckInfoDateAndWhetherAndRemark(@RequestParam("checkInfoId") String checkInfoId,
													@RequestParam("whether") String whether,
													@RequestParam("remarks") String remarks,
													@RequestParam("createDate") String createDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("checkInfoId", Integer.parseInt(checkInfoId));
		map.put("whether", Integer.parseInt(whether));
		map.put("remarks", remarks);
		map.put("checkDate", createDate);
		return checkInfoService.modified(map);
	}
	@RequestMapping(value="/modifiCheckInfo")
	@ResponseBody
	boolean modifiCheckInfo(@RequestParam("checkInfoId") String checkInfoId,
							@RequestParam("whether") String whether,
							@RequestParam("remarks") String remarks,
							@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("checkInfoId", Integer.parseInt(checkInfoId));
		map.put("whether", Integer.parseInt(whether));
		map.put("remarks", remarks);
		map.put("account", account);
		return checkInfoService.modified(map);
	}
	/**
	 * 管理员获取检疫信息
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetCheckInfos")
	@ResponseBody
	JSONObject adminGetCheckInfos(@RequestParam("pageNumber") String pageNumber,
			@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<CheckInfo> checkInfos = checkInfoService.queryBySheepIdAndAccount(map);
		JSONArray jsonArray = JSONArray.fromObject(checkInfos);
		JSONObject json = new JSONObject();
		json.put("checkInfos", jsonArray);
		map.remove("limit");
		json.put("size", checkInfoService.queryBySheepIdAndAccount(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
	/**
	 * 管理员带条件查询检疫信息
	 * @param key
	 * @param date
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetCheckInfosBy")
	@ResponseBody
	JSONObject adminGetCheckInfosBy(@RequestParam("key") String key,
			@RequestParam("date") String date,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		if(!key.equals("")) {
			map.put("key", key);
		}
		if(!date.equals("")) {
			map.put("startDate", date.split(" - ")[0].trim());
			map.put("stopDate", date.split(" - ")[1].trim());
		}
		List<CheckInfo> checkInfos = checkInfoService.queryBySheepIdAndAccount(map);
		JSONArray jsonArray = JSONArray.fromObject(checkInfos);
		JSONObject json = new JSONObject();
		json.put("checkInfos", jsonArray);
		map.remove("limit");
		json.put("size", checkInfoService.queryBySheepIdAndAccount(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
}

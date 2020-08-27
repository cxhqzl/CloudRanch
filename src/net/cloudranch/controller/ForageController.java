package net.cloudranch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cloudranch.domain.Forage;
import net.cloudranch.service.ForageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ForageController {

	@Autowired
	@Qualifier("forageService")
	private ForageService forageService;
	/**
	 * 添加
	 * @param forageName
	 * @param produceDate
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addForage")
	@ResponseBody
	boolean add(@RequestParam("forageName") String forageName,
			@RequestParam("produceDate") String produceDate,
			@RequestParam("account") String account) {
		Forage forage = new Forage(0,forageName,produceDate,account);
		return forageService.add(forage);
	}
	/**
	 * 删除
	 * @param forageId
	 * @return
	 */
	@RequestMapping(value="/delForage")
	@ResponseBody
	boolean del(@RequestParam("forageId") String forageId) {
		return forageService.del(Integer.parseInt(forageId));
	}
	/**
	 * 修改信息
	 * @param forageId
	 * @param forageName
	 * @param produceDate
	 * @return
	 */
	@RequestMapping(value="/updateForage")
	@ResponseBody
	boolean update(@RequestParam("forageId") String forageId,
			@RequestParam("forageName") String forageName,
			@RequestParam("produceDate") String produceDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("forageId", Integer.parseInt(forageId));
		map.put("forageName", forageName);
		map.put("produceDate", produceDate);
		return forageService.modified(map);
	}
	/**
	 * 批量获取饲料名
	 * @param forageIds
	 * @return
	 */
	@RequestMapping(value="/getForages")
	@ResponseBody
	JSONObject getForageName(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<Forage> forages = forageService.query(map);
		String strs = "";
		for(int i=0;i<forages.size();i++) {
			strs += forages.get(i).getForageId() + "#" + forages.get(i).getForageName() + ",";
		}
		JSONObject json = new JSONObject();
		json.put("forages", strs.substring(0,strs.length()-1));
		return json;
	}
	
	@RequestMapping(value="/queryForage")
	@ResponseBody
	JSONObject query(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<Forage> forages = forageService.query(map);
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<forages.size();i++) {
			JSONObject json = JSONObject.fromObject(forages.get(i));
			jsonArray.add(json);
		}
		JSONObject json = new JSONObject();
		json.put("forages", forages);
		return json;
	}
	/**
	 * 分页查询
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/queryForages")
	@ResponseBody
	JSONObject queryForages(@RequestParam("account") String account,
						@RequestParam("pageNumber") String pageNumber,
						@RequestParam("limit") String limit) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex",beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<Forage> forages = forageService.query(map);
		json.put("forages", JSONArray.fromObject(forages));
		map.remove("limit");
		json.put("size", forageService.query(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
	/**
	 * 管理员获取饲料信息
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetForages")
	@ResponseBody
	JSONObject adminGetForages(@RequestParam("pageNumber") String pageNumber,
						@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex",beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<Forage> forages = forageService.query(map);
		JSONArray jsonArray = JSONArray.fromObject(forages);
		JSONObject json = new JSONObject();
		json.put("forages", jsonArray);
		map.remove("limit");
		json.put("size", forageService.query(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
	/**
	 * 管理员带条件查询饲料
	 * @param key
	 * @param date
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetForagesBy")
	@ResponseBody
	JSONObject adminGetForagesBy(@RequestParam("key") String key,
						@RequestParam("date") String date,
						@RequestParam("pageNumber") String pageNumber,
						@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex",beginIndex);
		map.put("limit", Integer.parseInt(limit));
		if(!key.equals("")) {
			map.put("key", key);
		}
		if(!date.equals("")) {
			map.put("startDate", date.split(" - ")[0].trim());
			map.put("stopDate", date.split(" - ")[1].trim());
		}
		List<Forage> forages = forageService.query(map);
		JSONArray jsonArray = JSONArray.fromObject(forages);
		JSONObject json = new JSONObject();
		json.put("forages", jsonArray);
		map.remove("limit");
		json.put("size", forageService.query(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
}

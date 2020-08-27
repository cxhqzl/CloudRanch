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

import net.cloudranch.domain.H2s;
import net.cloudranch.service.H2sService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class H2sController {
	
	@Autowired
	@Qualifier("h2sService")
	private H2sService h2sService;
	
	/**
	 * 添加H2s数据
	 * @param concen
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="addH2s")
	@ResponseBody
	boolean addH2s(@RequestParam("concen") double concen,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (concen >= 100) {
			alarmType = "硫化氢浓度过高";
		}
		H2s h2s = new H2s(sensorId, concen, alarmType, createDate);
		return h2sService.add(h2s);
	}
	/**
	 * 删除H2s表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="delH2s")
	@ResponseBody
	boolean delH2s(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return h2sService.delete(map);
	}
	/**
	 * 查询H2s表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="queryH2ss")
	@ResponseBody
	JSONObject queryH2ss(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<H2s> h2ss = h2sService.query(map);
		json.put("h2ss", JSONArray.fromObject(h2ss));
		return json;
	}
}

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

import net.cloudranch.domain.Nh3;
import net.cloudranch.service.Nh3Service;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class Nh3Controller {
	
	@Autowired
	@Qualifier("nh3Service")
	private Nh3Service nh3Service;
	
	/**
	 * 添加Nh3数据
	 * @param concen
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="addNh3")
	@ResponseBody
	boolean addNh3(@RequestParam("concen") double concen,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (concen >= 100) {
			alarmType = "氨气浓度过高";
		}
		Nh3 nh3 = new Nh3(sensorId,concen,alarmType,createDate);
		return nh3Service.add(nh3);
	}
	/**
	 * 删除Nh3表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="delNh3")
	@ResponseBody
	boolean delNh3(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return nh3Service.delete(map);
	}
	/**
	 * 查询Nh3表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="queryNh3s")
	@ResponseBody
	JSONObject queryNh3s(@RequestParam("createDate") String createDate,
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
		List<Nh3> nh3s = nh3Service.query(map);
		json.put("nh3s", JSONArray.fromObject(nh3s));
		return json;
	}
}

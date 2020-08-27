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

import net.cloudranch.domain.Co2;
import net.cloudranch.service.Co2Service;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class Co2Controller {
	
	@Autowired
	@Qualifier("co2Service")
	private Co2Service co2Service;
	
	/**
	 * 添加CO2数据
	 * @param concen
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="addCo2")
	@ResponseBody
	boolean addCo2(@RequestParam("concen") double concen,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (concen >= 100) {
			alarmType = "二氧化碳浓度过高";
		}
		Co2 co2 = new Co2(sensorId,concen,alarmType,createDate);
		return co2Service.add(co2);
	}
	/**
	 * 删除CO2表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="delCo2")
	@ResponseBody
	boolean delCo2(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return co2Service.delete(map);
	}
	/**
	 * 查询空气表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="queryCo2s")
	@ResponseBody
	JSONObject queryCo2s(@RequestParam("createDate") String createDate,
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
		List<Co2> co2s = co2Service.query(map);
		json.put("airs", JSONArray.fromObject(co2s));
		return json;
	}
}

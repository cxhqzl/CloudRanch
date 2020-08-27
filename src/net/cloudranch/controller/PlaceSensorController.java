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

import net.cloudranch.domain.PlaceSensor;
import net.cloudranch.service.PlaceSensorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class PlaceSensorController {
	
	@Autowired
	@Qualifier("placeSensorService")
	private PlaceSensorService placeSensorService22;
	
	/**
	 * É¾³ý¼ÇÂ¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delPlaceSensor")
	@ResponseBody
	boolean delPlaceSensor(@RequestParam("id") int id) {
		return placeSensorService22.del(id);
	}
	/**
	 * ÐÞ¸Ä
	 * @param id
	 * @param placeId
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/modifiPlaceSensor")
	@ResponseBody
	boolean modifiPlaceSensor(@RequestParam("id") int id,
			@RequestParam("placeId") int placeId,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return placeSensorService22.modifiedPlaceSensor(map);
	}
	/**
	 * ²éÑ¯
	 * @param placeId
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/queryPlaceSensors")
	@ResponseBody
	JSONObject queryPlaceSensors(@RequestParam("placeId") int placeId,
			@RequestParam("sensorId") int sensorId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		List<PlaceSensor> placeSensors = placeSensorService22.queryPlaceSensors(map);
		json.put("placeSensors", JSONArray.fromObject(placeSensors));
		return json;
	}
}

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
import net.cloudranch.domain.Sensor;
import net.cloudranch.service.PlaceSensorService;
import net.cloudranch.service.SensorService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class SensorController {
	
	@Autowired
	@Qualifier("sensorService")
	private SensorService sensorService;
	@Autowired
	@Qualifier("placeSensorService")
	private PlaceSensorService placeSensorService;
	
	/**
	 * 添加传感器
	 * @param type
	 * @param mac
	 * @param interval
	 * @param isReport
	 * @param status
	 * @param battery
	 * @param nodeId
	 * @param picture
	 * @param icon
	 * @return
	 */
	@RequestMapping(value="/addSensor")
	@ResponseBody
	boolean addSensor(@RequestParam("type") String type,
			@RequestParam("sensorName") String sensorName,
			@RequestParam("mac") String mac,
			@RequestParam("interval") int interval,
			@RequestParam("isReport") int isReport,
			@RequestParam("status") String status,
			@RequestParam("battery") double battery,
			@RequestParam("placeId") int placeId,
			@RequestParam("picture") String picture,
			@RequestParam("icon") String icon) {
		Sensor sensor = new Sensor(0, type,sensorName, mac, interval, isReport, status, battery, BasicUtils.getDatetime(), placeId, picture, icon);
		boolean flag = sensorService.addSensor(sensor);
		if(flag) {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Sensor> sensors = sensorService.querySensors(map);
			PlaceSensor ps = new PlaceSensor(0, placeId, sensors.get(sensors.size()-1).getSensorId(),type);
			return placeSensorService.addPlaceSensor(ps);
		}
		return false;
	}
	/**
	 * 删除传感器
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delSensor")
	@ResponseBody
	boolean delSensor(@RequestParam("sensorId") int sensorId) {
		return sensorService.delSensor(sensorId);
	}
	/**
	 * 修改传感器
	 * @param type
	 * @param mac
	 * @param interval
	 * @param isReport
	 * @param status
	 * @param battery
	 * @param nodeId
	 * @param picture
	 * @param icon
	 * @return
	 */
	@RequestMapping(value="/modifiSensor")
	@ResponseBody
	boolean modifiSensor(@RequestParam("type") String type,
			@RequestParam("mac") String mac,
			@RequestParam("interval") int interval,
			@RequestParam("isReport") int isReport,
			@RequestParam("status") String status,
			@RequestParam("battery") double battery,
			@RequestParam("nodeId") int nodeId,
			@RequestParam("picture") String picture,
			@RequestParam("icon") String icon) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!type.equals("")) {
			map.put("type", type);
		}
		if(!mac.equals("")) {
			map.put("mac", mac);
		}
		if(interval != -1) {
			map.put("interval", interval);
		}
		if(isReport != -1) {
			map.put("isReport", isReport);
		}
		if(!status.equals("")) {
			map.put("status", status);
		}
		if(battery != -1) {
			map.put("battery", battery);
		}
		if(nodeId != -1) {
			map.put("placeId", nodeId);
		}
		if(!picture.equals("")) {
			map.put("picture", picture);
		}
		if(!icon.equals("")) {
			map.put("icon", icon);
		}
		return sensorService.modifiSensor(map);
	}
	/**
	 * 查询传感器信息
	 * @param sensorId
	 * @param nodeId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/querySensors")
	@ResponseBody
	JSONObject querySensors(@RequestParam("sensorId") int sensorId,
			@RequestParam("placeId") int placeId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(limit != -1 && pageNumber > 0) {
			map.put("limit", limit);
			map.put("pageNumber", (pageNumber - 1) * limit);
		}
		List<Sensor> sensors = sensorService.querySensors(map);
		json.put("sensors", JSONArray.fromObject(sensors));
		return json;
	}
}

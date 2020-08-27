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

import net.cloudranch.domain.SiteLocation;
import net.cloudranch.service.SiteLocationService;
import net.sf.json.JSONObject;

@Controller
public class SiteLocationController {
	
	@Autowired
	@Qualifier("siteLocationService")
	private SiteLocationService siteLocationService;
	
	/**
	 * 添加站点位置记录
	 * @param lng
	 * @param lat
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/addSiteLocation")
	@ResponseBody
	JSONObject addSiteLocation(@RequestParam("number") int number,
			@RequestParam("locationName") String locationName,
			@RequestParam("lng") double lng,
			@RequestParam("lat") double lat,
			@RequestParam("siteId") int siteId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("number", number);
		boolean flag = siteLocationService.numberExists(map);
		if(flag) {
			json.put("type", 1);//编号已存在
			return json;
		}
		SiteLocation siteLocation = new SiteLocation(0,number,locationName,lng,lat,siteId);
		flag = siteLocationService.addSiteLocation(siteLocation);
		json.put("flag", flag);
		if(flag) {
			json.put("type", 0);//录入成功
		}else {
			json.put("type", 2);//录入失败
		}
		return json;
	}
	@RequestMapping(value="/addSiteLocations")
	@ResponseBody
	boolean addSiteLocations(@RequestParam("lngLats") String lngLats,
			@RequestParam("siteId") int siteId) {
		String[] strs = lngLats.split("@");
		boolean flag = false;
		for(int i=0;i<strs.length;i++) {
			SiteLocation siteLocation = new SiteLocation(0,i,i+"",Double.parseDouble(strs[i].split(",")[0]),Double.parseDouble(strs[i].split(",")[1]),siteId);
			flag = siteLocationService.addSiteLocation(siteLocation);
		}
		return flag;
	}
	/**
	 * 删除站点位置记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delSiteLocation")
	@ResponseBody
	boolean delSiteLocation(@RequestParam("id") int id) {
		return siteLocationService.delSiteLocation(id);
	}
	/**
	 * 修改站点位置
	 * @param id
	 * @param image
	 * @return
	 */
	@RequestMapping(value="/modifiySiteLocation")
	@ResponseBody
	boolean modifiySiteLocation(@RequestParam("id") int id,
			@RequestParam("lng") double lng,
			@RequestParam("lat") double lat) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("lng", lng);
		map.put("lat", lat);
		return siteLocationService.modifiySiteLocation(map);
	}
	/**
	 * 查询站点位置信息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/querySiteLocations")
	@ResponseBody
	JSONObject querySiteLocations(@RequestParam("siteId") int siteId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		List<SiteLocation> siteLocations = siteLocationService.querySiteLocations(map);
		String str = "";
		for(SiteLocation sl : siteLocations) {
			str += sl.getLng() + "," + sl.getLat() + "@";
		}
		str = str.substring(0,str.length()-1);
		json.put("lngLats", str);
		return json;
	}
}

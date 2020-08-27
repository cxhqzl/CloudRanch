package net.cloudranch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cloudranch.domain.PlaceLocation;
import net.cloudranch.service.PlaceLocationService;
import net.sf.json.JSONObject;

@Controller
public class PlaceLocationController {
	
	@Autowired
	@Qualifier("placeLocationService")
	private PlaceLocationService placeLocationService;
	
	/**
	 * 添加地块位置记录
	 * @param lng
	 * @param lat
	 * @param PlaceId
	 * @return
	 */
	@RequestMapping(value="/addPlaceLocation")
	@ResponseBody
	JSONObject addPlaceLocation(@RequestParam("number") int number,
			@RequestParam("locationName") String locationName,
			@RequestParam("lng") double lng,
			@RequestParam("lat") double lat,
			@RequestParam("placeId") int placeId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("number", number);
		boolean flag = placeLocationService.numberExists(map);
		if(flag) {
			json.put("type", 1);//编号已存在
			return json;
		}
		PlaceLocation PlaceLocation = new PlaceLocation(0,number,locationName,lng,lat,placeId);
		flag = placeLocationService.addPlaceLocation(PlaceLocation);
		json.put("flag", flag);
		if(flag) {
			json.put("type", 0);//录入成功
		}else {
			json.put("type", 2);//录入失败
		}
		return json;
	}
	@RequestMapping(value="/addPlaceLocations")
	@ResponseBody
	boolean addPlaceLocations(@RequestParam("lngLats") String lngLats,
			@RequestParam("placeId") int placeId) {
		String[] strs = lngLats.split("@");
		boolean flag = false;
		for(int i=0;i<strs.length;i++) {
			PlaceLocation pl = new PlaceLocation(0, i, i+"", Double.parseDouble(strs[i].split(",")[0]), Double.parseDouble(strs[i].split(",")[1]), placeId);
			flag = placeLocationService.addPlaceLocation(pl);
		}
		return flag;
	}
	/**
	 * 删除地块位置记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delPlaceLocation")
	@ResponseBody
	boolean delPlaceLocation(@RequestParam("id") int id) {
		return placeLocationService.delPlaceLocation(id);
	}
	/**
	 * 修改地块位置
	 * @param id
	 * @param image
	 * @return
	 */
	@RequestMapping(value="/modifiyPlaceLocation")
	@ResponseBody
	boolean modifiyPlaceLocation(@RequestParam("id") int id,
			@RequestParam("lng") double lng,
			@RequestParam("lat") double lat) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("lng", lng);
		map.put("lat", lat);
		return placeLocationService.modifiyPlaceLocation(map);
	}
	/**
	 * 查询地块位置信息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/queryPlaceLocations")
	@ResponseBody
	JSONObject queryPlaceLocations(@RequestParam("placeIds") String placeIds) {
		List<Integer> ids = new ArrayList<Integer>();
		if(placeIds.indexOf(",") == -1) {
			ids.add(Integer.parseInt(placeIds));
		}else {
			String[] strs = placeIds.split(",");
			for(String str : strs) {
				ids.add(Integer.parseInt(str));
			}
		}
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		String str = "";
		for(int id : ids) {
			map.put("placeId",id);
			List<PlaceLocation> placeLocations = placeLocationService.queryPlaceLocations(map);
			for(PlaceLocation pl : placeLocations) {
				str += pl.getLng() + "," + pl.getLat() + "@";
			}
			str = str.substring(0,str.length()-1);
			str += "#";
		}
		str = str.substring(0,str.length()-1);
		json.put("lngLats", str);
		return json;
	}
}

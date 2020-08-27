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

import net.cloudranch.domain.Overall;
import net.cloudranch.domain.Place;
import net.cloudranch.service.OverallService;
import net.cloudranch.service.PlaceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class OverallController {
	
	@Autowired
	@Qualifier("overallService")
	private OverallService overallService;
	@Autowired
	@Qualifier("placeService")
	private PlaceService placeService;
	/**
	 * 添加全景图记录
	 * @param image
	 * @param overallURL
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/addOverall")
	@ResponseBody
	boolean addOverall(@RequestParam("image") String image,
			@RequestParam("overallURL") String overallURL,
			@RequestParam("placeId") int placeId) {
		Overall o = new Overall(0,image,overallURL,placeId);
		return overallService.addOverall(o);
	}
	/**
	 * 删除全景图记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delOverall")
	@ResponseBody
	boolean delOverall(@RequestParam("id") int id) {
		return overallService.delOverall(id);
	}
	/**
	 * 修改全景图记录
	 * @param id
	 * @param image
	 * @param overallURL
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/modifiOverall")
	@ResponseBody
	boolean modifiOverall(@RequestParam("id") int id,
			@RequestParam("image") String image,
			@RequestParam("overallURL") String overallURL,
			@RequestParam("placeId") int placeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		if(!image.equals("")) {
			map.put("image", image);
		}
		if(!overallURL.equals("")) {
			map.put("overallURL", overallURL);
		}
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		return overallService.modifiedOverall(map);
	}
	/**
	 * 查询全景图记录
	 * @param placeId
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryOveralls")
	@ResponseBody
	JSONObject queryOveralls(@RequestParam("placeId") int placeId,
			@RequestParam("id") int id) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(id != -1) {
			map.put("id", id);
		}
		List<Overall> os =  overallService.queryOveralls(map);
		JSONArray places = new JSONArray();
		map.clear();
		for(Overall o : os) {
			map.put("placeId", o.getPlaceId());
			places.add(placeService.queryPlaces(map).get(0));
		}
		json.put("overalls", JSONArray.fromObject(os));
		json.put("places", places);
		return json;
	}
	/**
	 * 查询站点下所有地块全景图
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/queryOverallsBySiteId")
	@ResponseBody
	JSONObject queryOverallsBySiteId(@RequestParam("siteId") int siteId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		List<Place> ps = placeService.queryPlaces(map);
		List<Overall> overalls = new ArrayList<Overall>();
		List<Place> places = new ArrayList<Place>();
		map.clear();
		for(Place p : ps) {
			map.put("placeId", p.getPlaceId());
			List<Overall> os =  overallService.queryOveralls(map);
			overalls.addAll(os);
			places.add(p);
		}
		json.put("overalls", JSONArray.fromObject(overalls));
		json.put("places", JSONArray.fromObject(places));
		return json;
	}
}

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

import net.cloudranch.domain.DividerPlace;
import net.cloudranch.service.DividerPlaceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class DividerPlaceController {
	
	@Autowired
	@Qualifier("dividerPlaceService")
	private DividerPlaceService dividerPlaceService;
	
	/**
	 * 添加地块切分
	 * @param crop
	 * @param square
	 * @param placeId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addDividerPlace")
	@ResponseBody
	boolean addDividerPlace(@RequestParam("crop") String crop,
			@RequestParam("square") double square,
			@RequestParam("placeId") int placeId,
			@RequestParam("account") String account) {
		DividerPlace dividerPlace = new DividerPlace(0, crop, square, placeId, account);
		return dividerPlaceService.addDividerPlace(dividerPlace);
	}
	/**
	 * 删除地块切分
	 * @param dividerId
	 * @return
	 */
	@RequestMapping(value="/delDividerPlace")
	@ResponseBody
	boolean delDividerPlace(@RequestParam("dividerId") int dividerId) {
		return dividerPlaceService.delDividerPlace(dividerId);
	}
	/**
	 * 修改地块切分
	 * @param crop
	 * @param square
	 * @param placeId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/modifiDividerPlace")
	@ResponseBody
	boolean modifiDividerPlace(@RequestParam("crop") String crop,
			@RequestParam("square") double square,
			@RequestParam("placeId") int placeId,
			@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!crop.equals("")) {
			map.put("crop", crop);
		}
		if(square != -1) {
			map.put("square", square);
		}
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(!account.equals("")) {
			map.put("account", account);
		}
		return dividerPlaceService.modifiDividerPlace(map);
	}
	/**
	 * 查询地块
	 * @param placeId
	 * @param siteId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryDividerPlaces")
	@ResponseBody
	JSONObject queryDividerPlaces(@RequestParam("placeId") int placeId,
			@RequestParam("dividerId") int dividerId,
			@RequestParam("account") String account,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(dividerId != -1) {
			map.put("dividerId", dividerId);
		}
		if(!account.equals("")) {
			map.put("account", account);
		}
		if(limit != -1 && pageNumber > 0) {
			map.put("limit", limit);
			map.put("pageNumber", (pageNumber - 1) * limit);
		}
		List<DividerPlace> dividerPlaces = dividerPlaceService.queryDividerPlaces(map);
		json.put("dividerPlaces", JSONArray.fromObject(dividerPlaces));
		return json;
	}
}

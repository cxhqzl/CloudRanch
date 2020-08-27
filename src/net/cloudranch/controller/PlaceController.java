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

import net.cloudranch.domain.AccountInfo;
import net.cloudranch.domain.Place;
import net.cloudranch.domain.Vido;
import net.cloudranch.service.AccountInfoService;
import net.cloudranch.service.PlaceSensorService;
import net.cloudranch.service.PlaceService;
import net.cloudranch.service.VidoService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class PlaceController {
	
	@Autowired
	@Qualifier("placeService")
	private PlaceService placeService;
	@Autowired
	@Qualifier("accountInfoService")
	private AccountInfoService accountInfoService1;
	@Autowired
	@Qualifier("placeSensorService")
	private PlaceSensorService placeSensorService1;
	@Autowired
	@Qualifier("vidoService")
	private VidoService vidoService;
	
	@RequestMapping(value="/addVido")
	@ResponseBody
	boolean addVido(@RequestParam("vidoName") String vidoName,
			@RequestParam("vidoUrl") String vidoUrl,
			@RequestParam("vidoType") String vidoType,
			@RequestParam("placeId") int placeId) {
		Vido vido = new Vido(0, vidoName, vidoUrl, vidoType, placeId);
		return vidoService.addVido(vido);
	}
	/**
	 * 删除视频
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delVido")
	@ResponseBody
	boolean delVido(@RequestParam("id") int id) {
		return vidoService.delVido(id);
	}
	/**
	 * 修改视频
	 * @param vidoName
	 * @param vidoUrl
	 * @param vidoType
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/modifiVido")
	@ResponseBody
	boolean modifiVido(@RequestParam("vidoName") String vidoName,
			@RequestParam("vidoUrl") String vidoUrl,
			@RequestParam("vidoType") String vidoType,
			@RequestParam("placeId") int placeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!vidoName.equals("")) {
			map.put("vidoName", vidoName);
		}
		if(!vidoUrl.equals("")) {
			map.put("vidoUrl", vidoUrl);
		}
		if(!vidoType.equals("")) {
			map.put("vidoType", vidoType);
		}
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		return vidoService.modifiedVido(map);
	}
	/**
	 * 查询视频
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/queryVidos")
	@ResponseBody
	JSONObject queryVidos(@RequestParam("placeId") int placeId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("placeId", placeId);
		List<Vido> vidos =  vidoService.queryVidos(map);
		json.put("videos", JSONArray.fromObject(vidos));
		return json;
	}
	/**
	 * 获取指定视频
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryVidoById")
	@ResponseBody
	JSONObject queryVidoById(@RequestParam("id") int id) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<Vido> vidos =  vidoService.queryVidos(map);
		json.put("video", JSONObject.fromObject(vidos.get(0)));
		return json;
	}
	/**
	 * 添加地块
	 * @param placeName
	 * @param square
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/addPlace")
	@ResponseBody
	JSONObject addPlace(@RequestParam("placeName") String placeName,
			@RequestParam("square") double square,
			@RequestParam("siteId") int siteId,
			@RequestParam("vidoUrl") String vidoUrl,
			@RequestParam("vidoName") String vidoName,
			@RequestParam("crop") String crop,
			@RequestParam("type") String type,
			@RequestParam("account") String account,
			@RequestParam("strokeColor") String strokeColor,
			@RequestParam("fillColor") String fillColor,
			@RequestParam("strokeOpacity") double strokeOpacity,
			@RequestParam("fillOpacity") double fillOpacity,
			@RequestParam("lngLats") String lngLats) {
		String vidoType = "image";
		if(vidoUrl.length() > 4) {
			if(vidoUrl.substring(0, 4).equals("http")) {
				vidoType = "vido";
			}
		}
		JSONObject json = new JSONObject();
		Place place = new Place(0, placeName,square, BasicUtils.getDatetime(), siteId, vidoType, vidoUrl,crop,type,account,strokeColor,fillColor,strokeOpacity,fillOpacity);
		boolean flag = placeService.addPlace(place);
		json.put("flag", flag);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Place> places = placeService.queryPlaces(map);
		json.put("placeId", places.get(places.size()-1).getPlaceId());
		Vido vido = new Vido(0, vidoName, vidoUrl, vidoType, places.get(places.size()-1).getPlaceId());
		vidoService.addVido(vido);
		return json;
	}
	/**
	 * 删除地块
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/delPlace")
	@ResponseBody
	boolean delPlace(@RequestParam("placeId") int placeId) {
		return placeService.delPlace(placeId);
	}
	/**
	 * 修改地块
	 * @param placeName
	 * @param square
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/modifiPlace")
	@ResponseBody
	boolean modifiPlace(@RequestParam("placeName") String placeName,
			@RequestParam("square") double square,
			@RequestParam("siteId") int siteId,
			@RequestParam("remarks") String remarks) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!placeName.equals("")) {
			map.put("placeName", placeName);
		}
		if(square != -1) {
			map.put("square", square);
		}
		if(siteId != -1) {
			map.put("siteId", siteId);
		}
		if(!remarks.equals("")) {
			map.put("remarks", remarks);
		}
		return placeService.modifiPlace(map);
	}
	/**
	 * 修改地块样式
	 * @param placeId
	 * @param fillColor
	 * @param fillOpacity
	 * @param strokeColor
	 * @param strokeOpacity
	 * @return
	 */
	@RequestMapping(value="/modifiPlaceStyle")
	@ResponseBody
	boolean modifiPlaceStyle(@RequestParam("placeId") int placeId,
			@RequestParam("fillColor") String fillColor,
			@RequestParam("fillOpacity") double fillOpacity,
			@RequestParam("strokeColor") String strokeColor,
			@RequestParam("strokeOpacity") double strokeOpacity) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("placeId", placeId);
		map.put("fillColor", fillColor);
		map.put("fillOpacity", fillOpacity);
		map.put("strokeColor", strokeColor);
		map.put("strokeOpacity", strokeOpacity);
		return placeService.modifiPlace(map);
	}
	/**
	 * 修改地块归属人
	 * @param account
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/changePlaceAccount")
	@ResponseBody
	boolean changePlaceAccount(@RequestParam("account") String account,
			@RequestParam("placeId") int placeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("placeId", placeId);
		return placeService.modifiPlace(map);
	}
	/**
	 * 查询地块
	 * @param placeId
	 * @param siteId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryPlaces")
	@ResponseBody
	JSONObject queryPlaces(@RequestParam("placeId") int placeId,
			@RequestParam("siteId") int siteId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("type") String type) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(siteId != -1) {
			map.put("siteId", siteId);
		}
		if(limit != -1 && pageNumber > 0) {
			map.put("limit", limit);
			map.put("pageNumber", (pageNumber - 1) * limit);
		}
		if(!type.equals("")) {
			map.put("type", type);
		}
		List<Place> places = placeService.queryPlaces(map);
		map.clear();
		for(Place p : places) {
			map.put("account", p.getAccount());
			p.setUserName(accountInfoService1.query(map).get(0).getUserName());
			map.clear();
			map.put("placeId", p.getPlaceId());
			p.setCount(placeSensorService1.queryPlaceSensors(map).size());
			map.clear();
		}
		json.put("places", JSONArray.fromObject(places));
		return json;
	}
	/**
	 * 农场主获取用户信息
	 * @param siteId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/famerGetUser")
	@ResponseBody
	JSONObject famerGetUser(@RequestParam("siteId") int siteId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account_no", "1");
		if(siteId != -1) {
			map.put("siteId", siteId);
		}
		if(limit != -1 && pageNumber > 0) {
			map.put("limit", limit);
			map.put("pageNumber", (pageNumber - 1) * limit);
		}
		List<Place> places = placeService.queryPlaces(map);
		JSONArray jsonA = new JSONArray();
		map.clear();
		for(Place p : places) {
			map.put("account", p.getAccount());
			jsonA.add(accountInfoService1.query(map).get(0));
		}
		json.put("places", JSONArray.fromObject(places));
		json.put("accounts", jsonA);
		return json;
	}
	/**
	 * 取消用户认领的地块
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/famerDelUser")
	@ResponseBody
	boolean famerDelUser(@RequestParam("placeId") int placeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("placeId", placeId);
		map.put("account", "1");
		return placeService.modifiPlace(map);
	}
}

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
	 * ���վ��λ�ü�¼
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
			json.put("type", 1);//����Ѵ���
			return json;
		}
		SiteLocation siteLocation = new SiteLocation(0,number,locationName,lng,lat,siteId);
		flag = siteLocationService.addSiteLocation(siteLocation);
		json.put("flag", flag);
		if(flag) {
			json.put("type", 0);//¼��ɹ�
		}else {
			json.put("type", 2);//¼��ʧ��
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
	 * ɾ��վ��λ�ü�¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delSiteLocation")
	@ResponseBody
	boolean delSiteLocation(@RequestParam("id") int id) {
		return siteLocationService.delSiteLocation(id);
	}
	/**
	 * �޸�վ��λ��
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
	 * ��ѯվ��λ����Ϣ
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

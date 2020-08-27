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

import net.cloudranch.domain.Place;
import net.cloudranch.domain.SiteImage;
import net.cloudranch.service.PlaceService;
import net.cloudranch.service.SiteImageService;
import net.cloudranch.service.impl.ViewService;
import net.cloudranch.viewDomain.Crops;
import net.cloudranch.viewDomain.SiteInfos;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class QueryInfosForViewController {
	
	@Autowired
	@Qualifier("viewService")
	private ViewService viewService;
	@Autowired
	@Qualifier("siteImageService")
	private SiteImageService siteImageService;
	@Autowired
	@Qualifier("placeService")
	private PlaceService placeService;
	/**
	 * 搜索站点-视图
	 * @param keys
	 * @return
	 */
	@RequestMapping(value="/searchSites")
	@ResponseBody
	JSONObject searchSite(@RequestParam("keys") String keys) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(keys.equals("")) {
			map.put("limit", 10);
		}else {
			map.put("searchSites", keys);
		}
		List<SiteInfos> sis = viewService.selectSiteInfos(map);
		List<Crops> cs = viewService.selectCrops(map);
		if(sis.size() <= 0) {
			for(Crops c : cs) {
				map.clear();
				map.put("siteId", c.getSiteId());
				SiteInfos si = viewService.selectSiteInfos(map).get(0);
				si.setCrops(c.getCrops());
				sis.add(si);
			}
		}else {
			for(SiteInfos s : sis) {
				for(Crops c : cs) {
					if(c.getSiteId() == s.getSiteId()) {
						s.setCrops(c.getCrops());
					}
				}
			}
		}
		for(SiteInfos si : sis) {
			map.clear();
			map.put("siteId", si.getSiteId());
			List<Place> places = placeService.queryPlaces(map);
			String str = "";
			for(Place p : places) {
				if(str.indexOf(p.getCrop()) == -1) {//作物种类去重
					str += p.getCrop() + "、";
				}
			}
			if(str.indexOf("、") != -1) {
				str = str.substring(0,str.length()-1);
			}
			si.setCrops(str);
			List<SiteImage> images = siteImageService.querySiteImages(map);
			if(images.size() > 0) {
				si.setImage(images.get(0).getImage());
			}
		}
		JSONObject json = new JSONObject();
		json.put("sites", JSONArray.fromObject(sis));
		JSONArray jsonA = new JSONArray();
		for(int i=0;i<sis.size();i++) {
			JSONArray jsonA1 = new JSONArray();
			jsonA1.add(sis.get(i).getLng());
			jsonA1.add(sis.get(i).getLat());
			jsonA.add(jsonA1);
		}
		JSONObject json1 = new JSONObject();
		json1.put("data", jsonA);
		json1.put("total", 5365);
		json1.put("rt_loc_cnt", 47764510);
		json1.put("errorno", 0);
		json1.put("NearestTime", "2014-08-29 15:20:00");
		json1.put("userTime", "2014-08-29 15:32:11");
		json.put("data", json1);
		return json;
	}
}

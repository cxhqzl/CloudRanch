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

import net.cloudranch.domain.AccountInfo;
import net.cloudranch.domain.CountBySite;
import net.cloudranch.domain.Place;
import net.cloudranch.domain.Site;
import net.cloudranch.domain.SiteImage;
import net.cloudranch.domain.SiteLocation;
import net.cloudranch.service.AccountInfoService;
import net.cloudranch.service.PlaceSensorService;
import net.cloudranch.service.PlaceService;
import net.cloudranch.service.SiteImageService;
import net.cloudranch.service.SiteLocationService;
import net.cloudranch.service.SiteService;
import net.cloudranch.service.VidoService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class SiteController {
	
	@Autowired
	@Qualifier("siteService")
	private SiteService siteService;
	@Autowired
	@Qualifier("siteImageService")
	private SiteImageService siteImageService;
	@Autowired
	@Qualifier("accountInfoService")
	private AccountInfoService accountInfoService;
	@Autowired
	@Qualifier("placeService")
	private PlaceService placeService;
	@Autowired
	@Qualifier("vidoService")
	private VidoService vidoService;
	@Autowired
	@Qualifier("placeSensorService")
	private PlaceSensorService placeSensorService;
	@Autowired
	@Qualifier("siteLocationService")
	private SiteLocationService siteLocationService;
	
	/**
	 * ���վ��
	 * @param siteName
	 * @param square
	 * @param province
	 * @param city
	 * @param county
	 * @param location
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addSite")
	@ResponseBody
	JSONObject addSite(@RequestParam("siteName") String siteName,
			@RequestParam("square") double square,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("county") String county,
			@RequestParam("location") String location,
			@RequestParam("lng") double lng,
			@RequestParam("lat") double lat,
			@RequestParam("account") String account,
			@RequestParam("remarks") String remarks) {
		Site site = new Site(0,siteName,square,province,city,county,location,lng,lat,BasicUtils.getDate(),account,remarks);
		boolean flag = siteService.addSite(site);
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Site> sites = siteService.adminGetSites(map);
		json.put("siteId", sites.get(sites.size()-1).getSiteId());
		return json;
	}
	/**
	 * ɾ��վ��
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/delSite")
	@ResponseBody
	boolean delSite(@RequestParam("siteId") int siteId) {
		return siteService.delSite(siteId);
	}
	/**
	 * �޸�վ��������
	 * @param siteId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/modifiySiteToAccount")
	@ResponseBody
	JSONObject modifiySiteToAccount(@RequestParam("siteId") int siteId,
			@RequestParam("account") String account) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		map.put("account", account);
		boolean flag = siteService.siteClaim(map);
		if(flag) {
			json.put("type", 1);//վ�㱻����
			return json;
		}
		flag = siteService.modifiySiteToAccount(map);
		if(!flag) {
			json.put("type", 2);//վ������ʧ��
			return json;
		}
		json.put("type", 0);//վ������ɹ�
		return json;
	}
	/**
	 * �޸�վ��
	 * @param siteId
	 * @param siteName
	 * @param square
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/modifiySite")
	@ResponseBody
	boolean modifiySite(@RequestParam("siteId") int siteId,
			@RequestParam("siteName") String siteName,
			@RequestParam("square") double square,
			@RequestParam("remarks") String remarks) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		if(!siteName.equals("")) {
			map.put("siteName", siteName);
		}
		if(!remarks.equals("")) {
			map.put("remarks", remarks);
		}
		if(square != -1) {
			map.put("square", square);
		}
		return siteService.modifiSite(map);
	}
	/**
	 * ��ѯվ����Ϣ
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/querySite")
	@ResponseBody
	JSONObject querySite(@RequestParam("siteId") int siteId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		Site site = siteService.querySite(map);
		json.put("site", JSONObject.fromObject(site));
		return json;
	}
	/**
	 * ũ������ȡ�˺���վ��
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/famerGetSites")
	@ResponseBody
	JSONObject famerGetSites(@RequestParam("account") String account) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<Site> sites = siteService.adminGetSites(map);
		List<String> lngLats = new ArrayList<String>();
		for(Site s : sites) {
			String str = "";
			map.clear();
			map.put("siteId", s.getSiteId());
			List<SiteLocation> sls = siteLocationService.querySiteLocations(map);
			for(SiteLocation sl : sls) {
				str += sl.getLng() + "," + sl.getLat() + "@";
			}
			lngLats.add(str.substring(0,str.length()-1));
		}
		json.put("sites", JSONArray.fromObject(sites));
		json.put("lngLats", JSONArray.fromObject(lngLats));
		return json;
	}
	/**
	 * ����ID��ѯվ��
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/querySiteBySiteId")
	@ResponseBody
	JSONObject querySiteBySiteId(@RequestParam("siteId") int siteId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		Site site = siteService.querySite(map);
		json.put("site", JSONObject.fromObject(site));
		List<SiteLocation> sls = siteLocationService.querySiteLocations(map);
		if(sls.size() > 0) {
			String str = "";
			for(SiteLocation sl : sls) {
				str += sl.getLng() + "," + sl.getLat() + "@";
			}
			json.put("lngLats", str.substring(0,str.length()-1));
		}
		return json;
	}
	/**
	 * ����Ա��ȡվ����Ϣ
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetSites")
	@ResponseBody
	JSONObject adminGetSites(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("limit") int limit) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(pageNumber > 0 || limit != -1) {
			map.put("beginIndex", (pageNumber - 1) * limit);
			map.put("limit", limit);
		}
		List<Site> sites = siteService.adminGetSites(map);
		json.put("sites", JSONArray.fromObject(sites));
		return json;
	}
	/**
	 * ��ȡ���վ��
	 * @return
	 */
	@RequestMapping(value="/getOneSite")
	@ResponseBody
	JSONObject getOneSite() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Site> sites = siteService.adminGetSites(map);
		int num = (int) Math.floor(Math.random()*sites.size());
		JSONObject json = new JSONObject();
		Site site = sites.get(num);
		map.put("siteId", site.getSiteId());
		List<SiteImage> sis = siteImageService.querySiteImages(map);
		if(sis.size() > 0) {
			site.setImage(sis.get(0).getImage());
		}else {
			site.setImage("error");
		}
		json.put("site", sites.get(num));
		return json;
	}
	/**
	 * ��ѯվ����Ϣ
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/querySiteInfos")
	@ResponseBody
	JSONObject querySiteInfos(@RequestParam("siteId") int siteId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		Site site = siteService.querySite(map);
		List<SiteImage> sis = siteImageService.querySiteImages(map);
		site.setImage(sis.get(0).getImage());//��ȡվ������ͼƬ
		map.clear();
		map.put("account", site.getAccount());
		List<AccountInfo> ais = accountInfoService.query(map);
		site.setAccount(ais.get(0).getAccount());//��ȡվ��������
		map.clear();
		map.put("siteId", siteId);
		List<Place> places = placeService.queryPlaces(map);
		String str = "";
		for(Place p : places) {
			if(str.indexOf(p.getCrop()) == -1) {//��������ȥ��
				str += p.getCrop() + "��";
			}
		}
		if(str.indexOf("��") != -1) {
			str = str.substring(0,str.length()-1);
		}
		site.setCrops(str.substring(0,str.length()-1));//��ȡվ������ֹ��������������
		return JSONObject.fromObject(site);
	}
	/**
	 * ��ѯ����վ��λ��
	 * @return
	 */
	@RequestMapping(value="/querySitesLnglat")
	@ResponseBody
	JSONObject querySitesLnglat() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Site> sites = siteService.adminGetSites(map);
		String str = "";
		String siteName = "";
		for(Site s : sites) {
			str += s.getLng() + "," + s.getLat() + "@";
			siteName += s.getSiteName() + ",";
		}
		if(str.indexOf("@") != -1) {
			str = str.substring(0,str.length()-1);
		}
		if(siteName.indexOf(",") != -1) {
			siteName = siteName.substring(0,siteName.length()-1);
		}
		JSONObject json = new JSONObject();
		json.put("lngLats", str);
		json.put("siteName", siteName);
		return json;
	}
	/**
	 * ��ѯվ���¸���������
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/queryCountBySite")
	@ResponseBody
	JSONObject queryCountBySite(@RequestParam("siteId") int siteId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		CountBySite cb = new CountBySite();
		cb.setSiteId(siteId);
		List<Place> places = placeService.queryPlaces(map);
		int placeCount = 0;
		int placeCountAni = 0;
		int count = 0;
		int noCount = 0;
		String str = "";
		int vidoCount = 0;
		int sensorCount = 0;
		for(Place p : places) {
			if(p.getType().equals("plant")) {
				placeCount++;
			}else {
				placeCountAni++;
			}
			if(!p.getAccount().equals("1")) {
				count++;
			}else {
				noCount++;
			}
			if(str.indexOf(p.getCrop()) == -1) {//��������ȥ��
				str += p.getCrop() + "��";
			}
			map.put("placeId", p.getPlaceId());
			vidoCount += vidoService.queryVidos(map).size();
			sensorCount += placeSensorService.queryPlaceSensors(map).size();
		}
		cb.setPlaceCount(placeCount);
		cb.setPlaceCountAni(placeCountAni);
		cb.setUserCount(count);//�û�����
		cb.setPlaceNotCount(noCount);//���еؿ�����
		if(str.length() == 0) {
			cb.setCropCount(0);
		}else {
			str = str.substring(0,str.length()-1);
			cb.setCropCount(str.split(",").length);//������������
		}
		cb.setVidoCount(vidoCount);//����ͷ����
		cb.setSensorCount(sensorCount);//����������
		JSONObject json = new JSONObject();
		json.put("counts", JSONObject.fromObject(cb));
		return json;
	}
}

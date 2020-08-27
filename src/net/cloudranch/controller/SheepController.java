package net.cloudranch.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.cloudranch.domain.Butcher;
import net.cloudranch.domain.Sheep;
import net.cloudranch.domain.SheepTransport;
import net.cloudranch.domain.UnitTransport;
import net.cloudranch.service.ButcherService;
import net.cloudranch.service.GrowInfoService;
import net.cloudranch.service.PlaceService;
import net.cloudranch.service.SheepService;
import net.cloudranch.service.SheepTransportService;
import net.cloudranch.service.UnitTransportService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class SheepController {
	
	@Autowired
	@Qualifier("sheepService")
	private SheepService sheepService;
	@Autowired
	@Qualifier("placeService")
	private PlaceService placeService1;
	@Autowired
	@Qualifier("butcherService")
	private ButcherService butcherService1;
	@Autowired
	@Qualifier("sheepTransportService")
	private SheepTransportService sheepTransportService1;
	@Autowired
	@Qualifier("unitTransportService")
	private UnitTransportService unitTransportService1;
	@Autowired
	@Qualifier("growInfoService")
	private GrowInfoService growInfoService1;
	
	/**
	 * 羊id生成接口
	 * @param number
	 * @return
	 */
	@RequestMapping(value="/getSheepId")
	@ResponseBody
	JSONObject getSheepId(@RequestParam("number") String number) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String date = sd.format(new Date());
		map.put("vagueSheepId", date);
		Map<String,Object> oldSheepIds = sheepService.querySheepId(map);
		List<String> newSheepIds = BasicUtils.produceSheepId(oldSheepIds, Integer.parseInt(number));
		JSONObject json = new JSONObject();
		json.put("sheepIds", newSheepIds);
		return json;
	}
	/**
	 * 添加羊信息
	 * @param sheepId
	 * @param sex
	 * @param oldId
	 * @param placeId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addSheep")
	@ResponseBody
	JSONObject addSheep(@RequestParam("sheepId") String sheepId,
			@RequestParam("sex") String sex,
			@RequestParam("source") String source,
			@RequestParam("placeId") String placeId,
			@RequestParam("fatherId") String fatherId,
			@RequestParam("motherId") String motherId,
			@RequestParam("account") String account,
			@RequestParam("file") MultipartFile imageFile) {
		JSONObject json = new JSONObject();
		Map<String,Object> map1 = new HashMap<String,Object>(); 
		map1.put("sheepId", sheepId);
		if(sheepService.weatherSheepId(map1)) {
			json.put("type", "2");//ID已存在
			return json;
		}
		if(!fatherId.equals("无")) {
			map1.put("sheepId", fatherId);
			if(!sheepService.weatherSheepId(map1)) {
				json.put("type", "3");//父ID不存在
				return json;
			}
		}
		if(!motherId.equals("无")) {
			map1.put("sheepId", motherId);
			if(!sheepService.weatherSheepId(map1)) {
				json.put("type", "4");//母ID不存在
				return json;
			}
		}
		String image = "";
		if(imageFile != null && imageFile.getOriginalFilename() != null && !imageFile.getOriginalFilename().equals("")) {
			String path = BasicUtils.getStoragePath("sheepImage\\startImage");
			try {
				String fileName = sheepId + imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."), imageFile.getOriginalFilename().length());
				image = fileName;
				File filepath = new File(path + "\\" + fileName);
				if(!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				imageFile.transferTo(filepath);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		String type = "";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		boolean flag = sheepService.weatherSheepId(map);
		if(!flag) {
			Sheep sheep = new Sheep(sheepId,sex,source,BasicUtils.getDate(),Integer.parseInt(placeId),fatherId,motherId,image,account);
			flag = sheepService.add(sheep);
			if(flag) {
				type = "0";//插入成功
				json.put("sheepId", sheepId);
				String path = BasicUtils.getStoragePath("sheepImage\\codeImage");
				File file = new File(path);
				File[] files = file.listFiles();
				for(File f : files) {
					if(!f.getName().equals(sheepId + ".png")) {
						f.delete();
					}
				}
			}else {
				type = "1";//插入失败
			}
		}else {
			type = "2";//id已存在
		}
		json.put("type", type);
		return json;
	}
	/**
	 * 查询账户地块下的所有羊信息
	 * @param request
	 * @param placeId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/querySheepsInfo")
	void querySheepsInfo(HttpServletRequest request,
			@RequestParam("placeId") String placeId,
			@RequestParam("account") String account,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("placeId", Integer.parseInt(placeId));
		map.put("account", account);
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<Sheep> sheeps = sheepService.queryByPlaceIdAndAccount(map);
		request.getSession().setAttribute("sheeps", sheeps);
		map.remove("limit");
		request.getSession().setAttribute("size", sheepService.queryCount(map));
		request.getSession().setAttribute("pageNumber", pageNumber);
	}
	/**
	 * 删除羊记录
	 * @param sheepId
	 * @return
	 */
	@RequestMapping(value="/delSheep")
	@ResponseBody
	boolean del(@RequestParam("sheepId") String sheepId) {
		boolean flag = sheepService.del(sheepId);
		return flag;
	}
	/**
	 * 修改羊性别和录入时间
	 * @param sheepId
	 * @param sex
	 * @param createDate
	 * @return
	 */
	@RequestMapping(value="/modifiedSheepSexAndDate")
	@ResponseBody
	boolean modifiedSheepSexAndDate(@RequestParam("sheepId") String sheepId,
									@RequestParam("sex") String sex,
									@RequestParam("createDate") String createDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("sex", sex);
		map.put("createDate", createDate);
		boolean flag = sheepService.modified(map);
		return flag;
	}
	@RequestMapping(value="/modifiedSheep")
	@ResponseBody
	boolean modifiedSheep(@RequestParam("sheepId") String sheepId,
							@RequestParam("sex") String sex,
							@RequestParam("source") String source,
							@RequestParam("account") String account,
							@RequestParam("placeId") String placeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("sex", sex);
		map.put("source", source);
		map.put("account", account);
		map.put("placeId", Integer.parseInt(placeId));
		boolean flag = sheepService.modified(map);
		return flag;
	}
	/**
	 * 获取养殖地经纬度
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/getLngAndLat")
	@ResponseBody
	JSONObject getLngAndLat(@RequestParam("sheepId") String sheepId,
						@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("account", account);
		JSONObject json = new JSONObject();
		Map<String,Double> maps = sheepService.getLngAndLat(map);
		if(maps == null) {
			json.put("lng", null);
		}else {
			json.put("lng", maps.get("lng"));
			json.put("lat", maps.get("lat"));
		}
		return json;
	}
	/**
	 * 获取羊数据MD5加密后结果
	 * @param sheepId
	 * @return
	 */
	@RequestMapping(value="/getSheepMD5")
	@ResponseBody
	JSONObject getSheepMD5(@RequestParam("sheepId") String sheepId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		JSONObject json = new JSONObject();
		Sheep sheep = sheepService.query(map);
		String nowData = BasicUtils.SHA(sheep.toString());
		json.put("nowData", nowData);
		return json;
	}
	/**
	 * 获取屠宰相关信息
	 * @param sheepId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getButcherInfo")
	@ResponseBody
	JSONObject getButcherInfo(@RequestParam("sheepId") String sheepId,
						@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("account", account);
		JSONObject json = new JSONObject();
		Sheep sheep = sheepService.query(map);
		if(sheep != null) {
			json.put("sex", sheep.getSex());
			json.put("createDate", sheep.getCreateDate());
		}
		Butcher butcher = butcherService1.query(map);
		if(butcher != null) {
			json.put("butcherDate", butcher.getButcherDate());
		}
		SheepTransport st = sheepTransportService1.getSheepTransport(map);
		if(st != null) {
			json.put("address", st.getProvince()+"-"+st.getCity()+"-"+st.getCounty());
			json.put("location", st.getLocation());
			json.put("lng", st.getLng());
			json.put("lat", st.getLat());
		}
		return json;
	}
	/**
	 * 获取运输信息
	 * @param sheepId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getTransportInfo")
	@ResponseBody
	JSONObject getTransportInfo(@RequestParam("sheepId") String sheepId,
						@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("account", account);
		JSONObject json = new JSONObject();
		Sheep sheep = sheepService.query(map);
		if(sheep != null) {
			json.put("sex", sheep.getSex());
			json.put("createDate", sheep.getCreateDate());
		}
		List<UnitTransport> uts = unitTransportService1.getUnitTransport(map);
		JSONArray unitTransports = new JSONArray();
		for(UnitTransport ut : uts) {
			JSONObject json1 = JSONObject.fromObject(ut);
			unitTransports.add(json1);
		}
		json.put("unitTransports", unitTransports);
		return json;
	}
	/**
	 * 按性别获取所有羊
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getSheepIdBySex")
	@ResponseBody
	JSONObject getSheepIdBySex(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!account.equals("")) {
			map.put("account", account);
		}
		map.put("sex", "公");
		JSONObject json = new JSONObject();
		List<Sheep> sheepGongs = sheepService.queryByPlaceIdAndAccount(map);
		json.put("sheepGongs", JSONArray.fromObject(sheepGongs));
		map.put("sex", "母");
		List<Sheep> sheepMus = sheepService.queryByPlaceIdAndAccount(map);
		json.put("sheepMus", JSONArray.fromObject(sheepMus));
		return json;
	}
	/**
	 * 获取所有ID
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getSheepIdAll")
	@ResponseBody
	JSONObject getSheepIdAll(@RequestParam("account") String account,
			@RequestParam("startDate") String startDate,
			@RequestParam("stopDate") String stopDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		if(startDate.equals("") && stopDate.equals("")) {
			startDate = BasicUtils.getDate();
			stopDate = BasicUtils.getDate();
		}
		map.put("startDate", startDate);
		map.put("stopDate", stopDate);
		JSONObject json = new JSONObject();
		List<Sheep> sheepGongs = sheepService.queryByPlaceIdAndAccount(map);
		json.put("sheepIds", JSONArray.fromObject(sheepGongs));
		return json;
	}
	
}

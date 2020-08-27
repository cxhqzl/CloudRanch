package net.cloudranch.controller;

import java.io.File;
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
import net.cloudranch.domain.ButcherInfo;
import net.cloudranch.service.ButcherInfoService;
import net.cloudranch.service.ButcherService;
import net.cloudranch.utils.BaiduMapUtils;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ButcherInfoAndButcherController {
	@Autowired
	@Qualifier("butcherInfoService")
	private ButcherInfoService butcherInfoService;
	
	@Autowired
	@Qualifier("butcherService")
	private ButcherService butcherService;
	
	/**
	 * 添加屠宰信息记录
	 * @param units
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addButcherInfo")
	@ResponseBody
	boolean addButcherInfo(@RequestParam("units") String units,
						@RequestParam("account") String account) {
		units = units.substring(0, units.length()-1);
		String[] Units = units.split(",");
		boolean flag = false;
		for(int i=0;i<Units.length;i++) {
			ButcherInfo butcherInfo = new ButcherInfo(Units[i].split("@")[0].trim(),Units[i].split("@")[1].trim(),Units[i].split("@")[2].trim(),account);
			flag = butcherInfoService.add(butcherInfo);
		}
		return flag;
	}
	/**
	 * 查询已录入ID并生成新ID
	 * @param account
	 * @param sheepId
	 * @param unitNumber
	 * @return
	 */
	@RequestMapping(value="/queryButcherInfo")
	@ResponseBody
	JSONObject query(@RequestParam("account") String account,
			@RequestParam("sheepId") String sheepId,
			@RequestParam("unitNumber") String unitNumber) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		Map<String,Object> oldUnitIds = butcherInfoService.queryUnitIdByAccount(map);
		boolean flag = false;
		JSONObject json = new JSONObject();
		String unitIds = String.valueOf(BasicUtils.produceUnitId(sheepId, Integer.parseInt(unitNumber),oldUnitIds));
		if(unitIds.length() != 0) {
			flag = true;
		}
		unitIds = unitIds.substring(1,unitIds.length()-1);
		json.put("unitIds", unitIds);
		json.put("flag", flag);
		return json;
	}
	/**
	 * 查询分块信息
	 * @param request
	 * @param sheepId
	 * @param account
	 * @param pageNumber
	 * @param limit
	 */
	@RequestMapping(value="/queryUnitInfo")
	void queryUnitInfo(HttpServletRequest request,
			@RequestParam("sheepId") String sheepId,
			@RequestParam("account") String account,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheepId", sheepId);
		map.put("account", account);
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<ButcherInfo> butcherInfos = butcherInfoService.queryBySheepIdAndAccount(map);
		map.remove("beginIndex");
		map.remove("limit");
		List<Butcher> butchers = butcherService.queryDateAndRemarks(map);
		Butcher butcher = butchers.get(0);
		for(int i=0;i<butcherInfos.size();i++) {
			butcherInfos.get(i).setButcherDate(butcher.getButcherDate());
			butcherInfos.get(i).setUnitNumber(butcherInfos.size());
			butcherInfos.get(i).setSheepId(sheepId);
		}
		request.getSession().setAttribute("butcherInfos", butcherInfos);
		map.remove("limit");
		request.getSession().setAttribute("size", butcherInfoService.queryCount(map));
		request.getSession().setAttribute("pageNumber", pageNumber);
	}
	
	@RequestMapping(value="/getUnitIdAll")
	@ResponseBody
	JSONObject getUnitIdAll(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<ButcherInfo> unitIds = butcherInfoService.queryBySheepIdAndAccount(map);
		JSONObject json = new JSONObject();
		json.put("unitIds", JSONArray.fromObject(unitIds));
		return json;
	}
	/**
	 * 删除
	 * @param unitId
	 * @return
	 */
	@RequestMapping(value="/delButcherInfo")
	@ResponseBody
	boolean delButcherInfo(@RequestParam("unitId") String unitId) {
		return butcherInfoService.del(unitId);
	}
	
	/**
	 * 添加信息
	 * @param butcherDate
	 * @param remarks
	 * @param sheepId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addButcher")
	@ResponseBody
	boolean addButcher(@RequestParam("butcherDate") String butcherDate,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("county") String county,
			@RequestParam("location") String location,
			@RequestParam("sheepId") String sheepId,
			@RequestParam("account") String account,
			@RequestParam("image") MultipartFile file) {
		String image = "";
		if(file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
			String path = BasicUtils.getStoragePath("sheepData\\butcherImage");
			try {
				String fileName = sheepId + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				image = fileName;
				File filepath = new File(path + "\\" + fileName);
				if(!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				file.transferTo(filepath);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		Map<String,Double> maps = BaiduMapUtils.getLngAndLat(province+city+county+location);
		Butcher butcher = new Butcher(0,butcherDate,image,province,city,county,location,maps.get("lng"),maps.get("lat"),sheepId,account);
		boolean flag = butcherService.add(butcher);
		return flag;
	}
	/**
	 * 获取日期和备注
	 * @param account
	 * @param sheepId
	 * @return
	 */
	@RequestMapping(value="/getButcherDateAndRemarks")
	@ResponseBody
	JSONObject getButcherDateAndRemarks(@RequestParam("account") String account,
									@RequestParam("sheepId") String sheepId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("sheepId", sheepId);
		List<Butcher> butchers = butcherService.queryDateAndRemarks(map);
		String dates = "";
		String remarks = "";
		for(int i=0;i<butchers.size();i++) {
			dates += butchers.get(i).getButcherDate() + ",";
		}
		JSONObject json = new JSONObject();
		json.put("dates", dates.substring(0,dates.length()-1));
		json.put("remarks", remarks.substring(0,remarks.length()-1));
		return json;
	}
	@RequestMapping(value="/modifiButcherInfo")
	@ResponseBody
	boolean modifiButcherInfo(@RequestParam("unitId") String unitId,
							@RequestParam("unitName") String unitName,
							@RequestParam("remarks") String remarks,
							@RequestParam("createDate") String createDate,
							@RequestParam("sheepId") String sheepId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unitId", unitId);
		map.put("unitName", unitName);
		boolean flag = butcherInfoService.modified(map);
		if(flag) {
			map.remove("unitId");
			map.remove("unitName");
			map.put("remarks", remarks);
			map.put("butcherDate", createDate);
			map.put("sheepId", sheepId);
			flag = butcherService.modified(map);
		}
		return flag;
	}
	@RequestMapping(value="/delButcher")
	@ResponseBody
	boolean del(@RequestParam("butcherId") String butcherId) {
		return butcherService.del(Integer.parseInt(butcherId));
	}
	
	@RequestMapping(value="/queryButcher")
	@ResponseBody
	JSONObject query() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("butcherId", 1);
		return JSONObject.fromObject(butcherService.query(map));
	}
	
}

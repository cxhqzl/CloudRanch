package net.cloudranch.controller;

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

import net.cloudranch.domain.Vaccine;
import net.cloudranch.service.VaccineService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class VaccineController {
	@Autowired
	@Qualifier("vaccineService")
	private VaccineService vaccineService;
	/**
	 * 添加记录
	 * @param vaccineName
	 * @param produceDate
	 * @param vaccineBrand
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addVaccine")
	@ResponseBody
	boolean add(@RequestParam("vaccineName") String vaccineName,
			@RequestParam("produceDate") String produceDate,
			@RequestParam("vaccineBrand") String vaccineBrand,
			@RequestParam("account") String account) {
		Vaccine vaccine = new Vaccine(0,vaccineName,produceDate,vaccineBrand,account);
		return vaccineService.add(vaccine);
	}
	/**
	 * 删除
	 * @param vaccineId
	 * @return
	 */
	@RequestMapping(value="/delVaccine")
	@ResponseBody
	boolean del(@RequestParam("vaccineId") String vaccineId) {
		return vaccineService.del(Integer.parseInt(vaccineId));
	}
	/**
	 * 修改
	 * @param vaccineId
	 * @param vaccineName
	 * @param produceDate
	 * @param vaccineBrand
	 * @return
	 */
	@RequestMapping(value="/updateVaccine")
	@ResponseBody
	boolean update(@RequestParam("vaccineId") String vaccineId,
			@RequestParam("vaccineName") String vaccineName,
			@RequestParam("produceDate") String produceDate,
			@RequestParam("vaccineBrand") String vaccineBrand,
			@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vaccineId", Integer.parseInt(vaccineId));
		map.put("vaccineName", vaccineName);
		map.put("produceDate", produceDate);
		map.put("vaccineBrand", vaccineBrand);
		if(!account.equals("")) {
			map.put("account", account);
		}
		return vaccineService.modified(map);
	}
	/**
	 * 查询疫苗名称
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getVaccines")
	@ResponseBody
	JSONObject queryVaccine(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<Vaccine> vaccines = vaccineService.query(map);
		String strs = "";
		for(int i=0;i<vaccines.size();i++) {
			strs += vaccines.get(i).getVaccineId() + "#" + vaccines.get(i).getVaccineName() + ",";
		}
		JSONObject json = new JSONObject();
		json.put("vaccines", strs.substring(0,strs.length()-1));
		return json;
	}
	/**
	 * 查询疫苗种类信息
	 * @param account
	 */
	@RequestMapping(value="/queryVaccine")
	@ResponseBody
	JSONObject query(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<Vaccine> vaccines = vaccineService.query(map);
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<vaccines.size();i++) {
			JSONObject json = JSONObject.fromObject(vaccines.get(i));
			jsonArray.add(json);
		}
		JSONObject json = new JSONObject();
		json.put("vaccines", vaccines);
		return json;
	}
	/**
	 * 分页查询
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/queryVaccines")
	@ResponseBody
	boolean queryVaccines(HttpServletRequest request,
							@RequestParam("account") String account,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex",beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<Vaccine> vaccines = vaccineService.query(map);
		request.getSession().setAttribute("vaccines", vaccines);
		map.remove("pageNumber");
		request.getSession().setAttribute("size", vaccineService.query(map).size());
		request.getSession().setAttribute("pageNumber", pageNumber);
		return true;
	}
	
	/**
	 * 管理员获取疫苗信息
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetVaccines")
	@ResponseBody
	JSONObject adminGetVaccines(@RequestParam("pageNumber") String pageNumber,
							@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex",beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<Vaccine> vaccines = vaccineService.query(map);
		JSONArray jsonArray = JSONArray.fromObject(vaccines);
		JSONObject json = new JSONObject();
		json.put("vaccines", jsonArray);
		map.remove("limit");
		json.put("size", vaccineService.query(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
	/**
	 * 管理员带条件查询疫苗
	 * @param key
	 * @param date
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/adminGetVaccinesBy")
	@ResponseBody
	JSONObject adminGetVaccinesBy(@RequestParam("key") String key,
							@RequestParam("date") String date,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("limit") String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		int beginIndex = (Integer.parseInt(pageNumber) - 1) * Integer.parseInt(limit);
		map.put("beginIndex",beginIndex);
		map.put("limit", Integer.parseInt(limit));
		if(!key.equals("")) {
			map.put("key", key);
		}
		if(!date.equals("")) {
			map.put("startDate", date.split(" - ")[0].trim());
			map.put("stopDate", date.split(" - ")[1].trim());
		}
		List<Vaccine> vaccines = vaccineService.query(map);
		JSONArray jsonArray = JSONArray.fromObject(vaccines);
		JSONObject json = new JSONObject();
		json.put("vaccines", jsonArray);
		map.remove("limit");
		json.put("size", vaccineService.query(map).size());
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
}

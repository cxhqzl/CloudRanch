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

import net.cloudranch.domain.TransportCompany;
import net.cloudranch.domain.TransportPeople;
import net.cloudranch.service.TransportCompanyService;
import net.cloudranch.service.TransportPeopleService;
import net.cloudranch.utils.BaiduMapUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class TransportCompanyController {

	@Autowired
	@Qualifier("transportCompanyService")
	private TransportCompanyService transportCompanyService;
	@Autowired
	@Qualifier("transportPeopleService")
	private TransportPeopleService transportPeopleService;
	
	@RequestMapping(value="/addTransportCompany")
	@ResponseBody
	boolean addTransportCompany(@RequestParam("name") String name,
								@RequestParam("province") String province,
								@RequestParam("city") String city,
								@RequestParam("county") String county,
								@RequestParam("location") String location,
								@RequestParam("account") String account) {
		Map<String,Double> map = BaiduMapUtils.getLngAndLat(province+city+county+location);
		TransportCompany tc = new TransportCompany(0,name,province,city,county,location,map.get("lng"),map.get("lat"),account);
		return transportCompanyService.add(tc);
	}
	@RequestMapping(value="/addTransportPeople")
	@ResponseBody
	boolean addTransportPeople(@RequestParam("name") String name,
							@RequestParam("phone") String phone,
							@RequestParam("carId") String carId,
							@RequestParam("province") String province,
							@RequestParam("city") String city,
							@RequestParam("county") String county,
							@RequestParam("location") String location,
							@RequestParam("companyId") String companyId,
							@RequestParam("account") String account) {
		Map<String,Double> map = BaiduMapUtils.getLngAndLat(province+city+county+location);
		TransportPeople tp = new TransportPeople(0,name,phone,carId,province,city,county,location,map.get("lng"),map.get("lat"),Integer.parseInt(companyId),account);
		return transportPeopleService.add(tp);
	}
	
	@RequestMapping(value="/getCompanysAndPeoples")
	@ResponseBody
	JSONObject getCompanysAndPeoples(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		JSONObject json = new JSONObject();
		JSONArray companys = new JSONArray();
		JSONArray peoples = new JSONArray();
		List<TransportCompany> cs = transportCompanyService.quertByAccount(map);
		for(int i=0;i<cs.size();i++) {
			JSONObject json1 = JSONObject.fromObject(cs.get(i));
			companys.add(json1);
		}
		json.put("companys", companys);
		List<TransportPeople> ps = transportPeopleService.quertByAccount(map);
		for(int i=0;i<ps.size();i++) {
			JSONObject json1 = JSONObject.fromObject(ps.get(i));
			peoples.add(json1);
		}
		json.put("peoples", peoples);
		return json;
	}
	/**
	 * 查询物流公司
	 * @param request
	 * @param account
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/queryTransportCompany")
	@ResponseBody
	JSONObject queryTransportCompany(@RequestParam("account") String account,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("limit") String limit) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		json.put("size", transportCompanyService.quertByAccount(map).size());
		int beginIndex = (Integer.parseInt(pageNumber) - 1)*Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<TransportCompany> transportCompanys = transportCompanyService.quertByAccount(map);
		json.put("companys", JSONArray.fromObject(transportCompanys));
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
	/**
	 * 查询运输人
	 * @param request
	 * @param account
	 * @param companyId
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/queryTransportPeople")
	@ResponseBody
	JSONObject queryTransportPeople(@RequestParam("account") String account,
							@RequestParam("companyId") String companyId,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("limit") String limit) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("companyId", companyId);
		json.put("size", transportPeopleService.quertByAccount(map).size());
		int beginIndex = (Integer.parseInt(pageNumber) - 1)*Integer.parseInt(limit);
		map.put("beginIndex", beginIndex);
		map.put("limit", Integer.parseInt(limit));
		List<TransportPeople> transportPeoples = transportPeopleService.quertByAccount(map);
		json.put("peoples", JSONArray.fromObject(transportPeoples));
		json.put("pageNumber", Integer.parseInt(pageNumber));
		return json;
	}
	/**
	 * 删除物流公司
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/delCompany")
	@ResponseBody
	boolean delCompany(@RequestParam("companyId") String companyId) {
		return transportCompanyService.del(Integer.parseInt(companyId));
	}
	/**
	 * 删除运输人
	 * @param peopleId
	 * @return
	 */
	@RequestMapping(value="/delPeople")
	@ResponseBody
	boolean delPeople(@RequestParam("peopleId") String peopleId) {
		return transportPeopleService.del(Integer.parseInt(peopleId));
	}
	/**
	 * 修改运输公司资料
	 * @param name
	 * @param province
	 * @param city
	 * @param county
	 * @param location
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/modifiCompanyData")
	@ResponseBody
	boolean modifiCompanyData(@RequestParam("name") String name,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("county") String county,
			@RequestParam("location") String location,
			@RequestParam("companyId") String companyId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyId", Integer.parseInt(companyId));
		if(!name.equals("")) {
			map.put("name", name);
		}
		if(!province.equals("") && !city.equals("") && !county.equals("") && !location.equals("")) {
			map.put("province", province);
			map.put("city", city);
			map.put("county", county);
			map.put("location", location);
			Map<String,Double> maps = BaiduMapUtils.getLngAndLat(province+city+county+location);
			map.put("lng", maps.get("lng"));
			map.put("lat", maps.get("lat"));
		}
		return transportCompanyService.modifiData(map);
	}
	/**
	 * 修改运输人信息
	 * @param name
	 * @param phone
	 * @param carId
	 * @param province
	 * @param city
	 * @param county
	 * @param location
	 * @param companyId
	 * @param peopleId
	 * @return
	 */
	@RequestMapping(value="/modifiPeopleData")
	@ResponseBody
	boolean modifiPeopleData(@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("carId") String carId,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("county") String county,
			@RequestParam("location") String location,
			@RequestParam("companyId") String companyId,
			@RequestParam("peopleId") String peopleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("peopleId", Integer.parseInt(peopleId));
		if(!name.equals("")) {
			map.put("name", name);
		}
		if(!phone.equals("")) {
			map.put("phone", phone);
		}
		if(!carId.equals("")) {
			map.put("carId", carId);
		}
		if(!province.equals("") && !city.equals("") && !county.equals("") && !location.equals("")) {
			map.put("province", province);
			map.put("city", city);
			map.put("county", county);
			map.put("location", location);
			Map<String,Double> maps = BaiduMapUtils.getLngAndLat(province+city+county+location);
			map.put("lng", maps.get("lng"));
			map.put("lat", maps.get("lat"));
		}
		if(!companyId.equals("")) {
			map.put("companyId", Integer.parseInt(companyId));
		}
		return transportPeopleService.modifiData(map);
	}
}

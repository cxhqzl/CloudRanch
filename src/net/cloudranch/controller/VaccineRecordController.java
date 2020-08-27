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
import net.cloudranch.domain.VaccineRecord;
import net.cloudranch.service.VaccineRecordService;
import net.cloudranch.service.VaccineService;
import net.cloudranch.utils.BasicUtils;

@Controller
public class VaccineRecordController {
	@Autowired
	@Qualifier("vaccineRecordService")
	private VaccineRecordService vaccineRecordService;
	@Autowired
	@Qualifier("vaccineService")
	private VaccineService vaccineService1;
	/**
	 * Ìí¼ÓÒßÃç¼ÇÂ¼
	 * @param sheepId
	 * @param vaccineId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addVaccineRecord")
	@ResponseBody
	boolean addVaccineRecord(@RequestParam("sheepId") String sheepId,
			@RequestParam("vaccineId") String vaccineId,
			@RequestParam("account") String account) {
		VaccineRecord vaccineRecord = new VaccineRecord(0,BasicUtils.getDate(),Integer.parseInt(vaccineId),sheepId,account);
		boolean flag = vaccineRecordService.add(vaccineRecord);
		return flag;
	}
	/**
	 * ²éÑ¯ÒßÃç¼ÇÂ¼
	 * @param request
	 * @param sheepId
	 * @param account
	 * @param pageNumber
	 * @param limit
	 */
	@RequestMapping(value="/queryVaccineRecord")
	void queryVaccineRecord(HttpServletRequest request,
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
		List<VaccineRecord> vaccineRecords = vaccineRecordService.queryBySheepIdAndAccount(map);
		for(VaccineRecord vr : vaccineRecords) {
			map.put("vaccineId", vr.getVaccineId());
			Vaccine v = vaccineService1.getVaccineName(map);
			vr.setVaccineName(v.getVaccineName());
			vr.setVaccineBrand(v.getVaccineBrand());
		}
		request.getSession().setAttribute("vaccineRecords", vaccineRecords);
		map.remove("limit");
		request.getSession().setAttribute("size", vaccineRecordService.queryCount(map));
		request.getSession().setAttribute("pageNumber", pageNumber);
	}
	
	/**
	 * É¾³ý
	 * @param vaccineRecordId
	 * @return
	 */
	@RequestMapping(value="/delVaccineRecord")
	@ResponseBody
	boolean delVaccineRecord(@RequestParam("vaccineRecordId") String vaccineRecordId) {
		return vaccineRecordService.del(Integer.parseInt(vaccineRecordId));
	}
	/**
	 * ÐÞ¸ÄÊ±¼äºÍÒßÃç
	 * @param vaccineRecordId
	 * @param createDate
	 * @param vaccineId
	 * @return
	 */
	@RequestMapping(value="/modifiVaccineRecordVaccineAndDate")
	@ResponseBody
	boolean modifiVaccineRecordVaccineAndDate(@RequestParam("vaccineRecordId") String vaccineRecordId,
											@RequestParam("createDate") String createDate,
											@RequestParam("vaccineId") String vaccineId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vaccineRecordId", Integer.parseInt(vaccineRecordId));
		map.put("recordDate", createDate);
		map.put("vaccineId", Integer.parseInt(vaccineId));
		return vaccineRecordService.modified(map);
	}
	@RequestMapping(value="/modifiVaccineRecord")
	@ResponseBody
	boolean modifiVaccineRecord(@RequestParam("vaccineRecordId") String vaccineRecordId,
											@RequestParam("account") String account,
											@RequestParam("vaccineId") String vaccineId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vaccineRecordId", Integer.parseInt(vaccineRecordId));
		map.put("account", account);
		map.put("vaccineId", Integer.parseInt(vaccineId));
		return vaccineRecordService.modified(map);
	}
}

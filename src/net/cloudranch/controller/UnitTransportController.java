package net.cloudranch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cloudranch.domain.UnitTransport;
import net.cloudranch.service.UnitTransportService;
import net.cloudranch.utils.BaiduMapUtils;

@Controller
public class UnitTransportController {

	@Autowired
	@Qualifier("unitTransportService")
	private UnitTransportService unitTransportService;
	
	@RequestMapping(value="/addUnitTransport")
	@ResponseBody
	boolean addUnitTransport(@RequestParam("startDate") String startDate,
						@RequestParam("stopDate") String stopDate,
						@RequestParam("province") String province,
						@RequestParam("city") String city,
						@RequestParam("county") String county,
						@RequestParam("location") String location,
						@RequestParam("unitId") String unitId,
						@RequestParam("peopleId") String peopleId,
						@RequestParam("account") String account) {
		Map<String,Double> map = BaiduMapUtils.getLngAndLat(province+city+county+location);
		UnitTransport ut = new UnitTransport(0,startDate,stopDate,province,city,county,location,
				map.get("lng"),map.get("lat"),unitId,Integer.parseInt(peopleId),account);
		return unitTransportService.add(ut);
	}
	
}

package net.cloudranch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cloudranch.domain.SheepTransport;
import net.cloudranch.service.SheepTransportService;
import net.cloudranch.utils.BaiduMapUtils;

@Controller
public class SheepTransportController {

	@Autowired
	@Qualifier("sheepTransportService")
	private SheepTransportService sheepTransportService;
	
	@RequestMapping(value="/addSheepTransport")
	@ResponseBody
	boolean addSheepTransport(@RequestParam("startDate") String startDate,
						@RequestParam("stopDate") String stopDate,
						@RequestParam("province") String province,
						@RequestParam("city") String city,
						@RequestParam("county") String county,
						@RequestParam("location") String location,
						@RequestParam("sheepId") String sheepId,
						@RequestParam("peopleId") String peopleId,
						@RequestParam("account") String account) {
		Map<String,Double> map = BaiduMapUtils.getLngAndLat(province+city+county+location);
		SheepTransport st = new SheepTransport(0,startDate,stopDate,province,city,county,location,
				map.get("lng"),map.get("lat"),sheepId,Integer.parseInt(peopleId),account);
		return sheepTransportService.add(st);
	}
}

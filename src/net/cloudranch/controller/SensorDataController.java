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

import net.cloudranch.domain.Air;
import net.cloudranch.domain.Dew;
import net.cloudranch.domain.Par;
import net.cloudranch.domain.PlaceSensor;
import net.cloudranch.domain.Radiation;
import net.cloudranch.domain.RainFall;
import net.cloudranch.domain.Soil;
import net.cloudranch.domain.Wind;
import net.cloudranch.service.AirService;
import net.cloudranch.service.DewService;
import net.cloudranch.service.ParService;
import net.cloudranch.service.PlaceSensorService;
import net.cloudranch.service.RadiationService;
import net.cloudranch.service.RainFallService;
import net.cloudranch.service.SoilService;
import net.cloudranch.service.WindService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class SensorDataController {
	
	@Autowired
	@Qualifier("airService")
	private AirService airService;
	@Autowired
	@Qualifier("soilService")
	private SoilService soilService;
	@Autowired
	@Qualifier("windService")
	private WindService windService;
	@Autowired
	@Qualifier("parService")
	private ParService parService;
	@Autowired
	@Qualifier("radiationService")
	private RadiationService radiationService;
	@Autowired
	@Qualifier("rainFallService")
	private RainFallService rainFallService;
	@Autowired
	@Qualifier("dewService")
	private DewService dewService;
	@Autowired
	@Qualifier("placeSensorService")
	private PlaceSensorService placeSensorService;
	
	
	@RequestMapping(value="/showEcharts")
	@ResponseBody
	JSONObject showEcharts(@RequestParam("sensorIds") String sensorIds) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		sensorIds = sensorIds.substring(0, sensorIds.length()-1);
		String[] sis = sensorIds.split(",");
		String endDate = BasicUtils.getDate();
		String beginDate = BasicUtils.getLastDate(7);
		for(String si : sis) {
			map.put("sensorId", Integer.parseInt(si));
			PlaceSensor ps = placeSensorService.queryPlaceSensors(map).get(0);
			switch(ps.getSensorType()) {
				case "空气温湿度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<Air> airs = airService.query(map);
					if(airs.size() > 0) {
						JSONArray ja = new JSONArray();
						JSONArray jb = new JSONArray();
						JSONArray jc = new JSONArray();
						for(Air a : airs) {
							ja.add(a.getTemp());
							jb.add(a.getHum());
							jc.add(a.getCreateDate());
						}
						json.put("airTemps", ja);
						json.put("airHums", jb);
						json.put("dates", jc);
					}
					break;
				case "土壤温湿度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<Soil> soils = soilService.query(map);
					if(soils.size() > 0) {
						JSONArray ja = new JSONArray();
						JSONArray jb = new JSONArray();
						for(Soil s : soils) {
							ja.add(s.getTemp());
							jb.add(s.getHum());
						}
						json.put("soilTemps", ja);
						json.put("soilHums", jb);
					}
					break;
				case "光照强度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<Radiation> radiations = radiationService.query(map);
					if(radiations.size() > 0) {
						JSONArray ja = new JSONArray();
						for(Radiation r : radiations) {
							ja.add(r.getIntensity());
						}
						json.put("radiations", ja);
					}
					break;
				case "光照辐射":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<Par> pars = parService.query(map);
					if(pars.size() > 0) {
						JSONArray ja = new JSONArray();
						for(Par p : pars) {
							ja.add(p.getIntensity());
						}
						json.put("pars", ja);
					}
					break;
				case "风速风向":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<Wind> winds = windService.query(map);
					if(winds.size() > 0) {
						JSONArray ja = new JSONArray();
						JSONArray jb = new JSONArray();
						for(Wind w : winds) {
							ja.add(w.getSpeed());
							jb.add(w.getDirection());
						}
						json.put("windSpeeds", ja);
						json.put("windDirections", jb);
					}
					break;
				case "露点温度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<Dew> dews = dewService.query(map);
					if(dews.size() > 0) {
						JSONArray ja = new JSONArray();
						for(Dew d : dews) {
							ja.add(d.getTemp());
						}
						json.put("dewTemps", ja);
					}
					break;
				case "降雨量":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginDate", beginDate);
					map.put("endDate", endDate);
					List<RainFall> rainFalls = rainFallService.query(map);
					if(rainFalls.size() > 0) {
						JSONArray ja = new JSONArray();
						for(RainFall rf : rainFalls) {
							ja.add(rf.getRainFall());
						}
						json.put("rainFalls", ja);
					}
					break;
			}
		}
		return json;
	}
	/**
	 * 管理员获取传感器数据
	 * @param sensorIds
	 * @return
	 */
	@RequestMapping(value="/adminGetSensorData")
	@ResponseBody
	JSONObject adminGetSensorData(@RequestParam("sensorIds") String sensorIds) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		sensorIds = sensorIds.substring(0, sensorIds.length()-1);
		String[] sis = sensorIds.split(",");
		JSONArray jsonA = new JSONArray();
		for(String si : sis) {
			map.put("sensorId", Integer.parseInt(si));
			PlaceSensor ps = placeSensorService.queryPlaceSensors(map).get(0);
			switch(ps.getSensorType()) {
				case "空气温湿度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<Air> airs = airService.query(map);
					if(airs.size() > 0) {
						jsonA.add(JSONObject.fromObject(airs.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
				case "土壤温湿度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<Soil> soils = soilService.query(map);
					if(soils.size() > 0) {
						jsonA.add(JSONObject.fromObject(soils.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
				case "光照强度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<Radiation> radiations = radiationService.query(map);
					if(radiations.size() > 0) {
						jsonA.add(JSONObject.fromObject(radiations.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
				case "光照辐射":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<Par> pars = parService.query(map);
					if(pars.size() > 0) {
						jsonA.add(JSONObject.fromObject(pars.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
				case "风速风向":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<Wind> winds = windService.query(map);
					if(winds.size() > 0) {
						jsonA.add(JSONObject.fromObject(winds.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
				case "露点温度":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<Dew> dews = dewService.query(map);
					if(dews.size() > 0) {
						jsonA.add(JSONObject.fromObject(dews.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
				case "降雨量":
					map.clear();
					map.put("sensorId", ps.getSensorId());
					map.put("beginIndex", 0);
					map.put("limit", 1);
					List<RainFall> rainFalls = rainFallService.query(map);
					if(rainFalls.size() > 0) {
						jsonA.add(JSONObject.fromObject(rainFalls.get(0)));
					}else {
						jsonA.add("--");
					}
					break;
			}
		}
		json.put("data", jsonA);
		return json;
	}
	/**
	 * 查询传感器 数据
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/querySensorData")
	@ResponseBody
	JSONObject querySensorData(@RequestParam("placeIds") String placeIds) {
		JSONObject json = new JSONObject();
		String[] pis = placeIds.split(",");
		Map<String,Object> map = new HashMap<String,Object>();
		List<PlaceSensor> pss = null;
		for(String pi : pis) {
			map.put("placeId", Integer.parseInt(pi));
			pss = placeSensorService.queryPlaceSensors(map);
			if(pss.size() > 0) {
				break;
			}
		}
		for(PlaceSensor ps : pss) {
			switch(ps.getSensorType()) {
				case "空气温湿度":
					if(!json.containsKey("air")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<Air> airs = airService.query(map);
						if(airs.size() > 0) {
							json.put("air", airs.get(0));
						}else {
							json.put("air", null);
						}
					}
					break;
				case "土壤温湿度":
					if(!json.containsKey("soil")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<Soil> soils = soilService.query(map);
						if(soils.size() > 0) {
							json.put("soil", soils.get(0));
						}else {
							json.put("soil", null);
						}
					}
					break;
				case "光照强度":
					if(!json.containsKey("radiation")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<Radiation> radiations = radiationService.query(map);
						if(radiations.size() > 0) {
							json.put("radiation", radiations.get(0));
						}else {
							json.put("radiation", null);
						}
					}
					break;
				case "光照辐射":
					if(!json.containsKey("par")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<Par> pars = parService.query(map);
						if(pars.size() > 0) {
							json.put("par", pars.get(0));
						}else {
							json.put("par", null);
						}
					}
					break;
				case "风速风向":
					if(!json.containsKey("wind")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<Wind> winds = windService.query(map);
						if(winds.size() > 0) {
							json.put("wind", winds.get(0));
						}else {
							json.put("wind", null);
						}
					}
					break;
				case "露点温度":
					if(!json.containsKey("dew")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<Dew> dews = dewService.query(map);
						if(dews.size() > 0) {
							json.put("dew", dews.get(0));
						}else {
							json.put("dew", null);
						}
					}
					break;
				case "降雨量":
					if(!json.containsKey("rainFall")) {
						map.clear();
						map.put("sensorId", ps.getSensorId());
						map.put("beginIndex", 0);
						map.put("limit", 1);
						List<RainFall> rainFalls = rainFallService.query(map);
						if(rainFalls.size() > 0) {
							json.put("rainFall", rainFalls.get(0));
						}else {
							json.put("rainFall", null);
						}
					}
					break;
			}
		}
		return json;
	}
	
	/**
	 * 添加露点温度
	 * @param temp
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addDew")
	@ResponseBody
	boolean addDew(@RequestParam("temp") double temp,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		Dew dew = new Dew( temp, "正常", createDate,sensorId);
		return dewService.add(dew);
	}
	/**
	 * 删除露点温度表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delDew")
	@ResponseBody
	boolean delDew(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return dewService.delete(map);
	}
	/**
	 * 查询露点温度表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryDews")
	@ResponseBody
	JSONObject queryDews(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Dew> dews = dewService.query(map);
		json.put("dews", JSONArray.fromObject(dews));
		return json;
	}
	
	/**
	 * 添加降雨
	 * @param rainFall
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addRainFall")
	@ResponseBody
	boolean addRainFall(@RequestParam("rainFall") double rainFall,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";   
		if (rainFall >= 150) {
			alarmType = "降雨量过高";
		}
		RainFall rf = new RainFall(rainFall, alarmType, createDate,sensorId);
		return rainFallService.add(rf);
	}
	/**
	 * 删除降雨表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delRainFall")
	@ResponseBody
	boolean delRainFall(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return rainFallService.delete(map);
	}
	/**
	 * 查询降雨表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryRainFalls")
	@ResponseBody
	JSONObject queryRainFalls(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<RainFall> rfs = rainFallService.query(map);
		json.put("rainFalls", JSONArray.fromObject(rfs));
		return json;
	}
	
	/**
	 * 添加光强数据
	 * @param intensity
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addRadiation")
	@ResponseBody
	boolean addRadiation(@RequestParam("intensity") double intensity,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (intensity <= 1) {
			alarmType = "光照较弱";
		} else if (intensity >= 150000) {
			alarmType = "光照过强";
		}
		Radiation radiation = new Radiation(sensorId, intensity, alarmType, createDate);
		return radiationService.add(radiation);
	}
	/**
	 * 删除radiation表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delRadiation")
	@ResponseBody
	boolean delRadiation(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return radiationService.delete(map);
	}
	/**
	 * 查询radiation表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryRadiations")
	@ResponseBody
	JSONObject queryRadiations(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Radiation> radiations = radiationService.query(map);
		json.put("pars", JSONArray.fromObject(radiations));
		return json;
	}
	
	/**
	 * 添加光辐数据
	 * @param intensity
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addPar")
	@ResponseBody
	boolean addPar(@RequestParam("intensity") double intensity,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (intensity <= 0) {
			alarmType = "有效光合辐射较弱";
		} else if (intensity >= 1) {
			alarmType = "有效光合辐射过强";
		}
		Par par = new Par(sensorId, intensity, alarmType, createDate);
		return parService.add(par);
	}
	/**
	 * 删除par表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delPar")
	@ResponseBody
	boolean delPar(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return parService.delete(map);
	}
	/**
	 * 查询par表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryPars")
	@ResponseBody
	JSONObject queryPars(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Par> pars = parService.query(map);
		json.put("pars", JSONArray.fromObject(pars));
		return json;
	}
	
	/**
	 * 添加wind表数据
	 * @param temp
	 * @param hum
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addWind")
	@ResponseBody
	boolean addWind(@RequestParam("speed") double speed,
			@RequestParam("direction") double direction,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (speed <= 10)
			alarmType = "风速过低";
		if (speed >= 30)
			alarmType = "风速过高";
		Wind wind = new Wind(sensorId, speed, direction, alarmType, createDate);
		return windService.add(wind);
	}
	/**
	 * 删除wind表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delWind")
	@ResponseBody
	boolean delWind(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return windService.delete(map);
	}
	/**
	 * 查询wind表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryWinds")
	@ResponseBody
	JSONObject queryWinds(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Wind> winds = windService.query(map);
		json.put("winds", JSONArray.fromObject(winds));
		return json;
	}
	
	/**
	 * 添加土壤表数据
	 * @param temp
	 * @param hum
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addSoil")
	@ResponseBody
	boolean addSoil(@RequestParam("temp") double temp,
			@RequestParam("hum") double hum,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (temp <= 20) {
			alarmType = "土壤温度过低";
		} else if (temp >= 40) {
			alarmType = "土壤温度过高";
		}
		Soil soil = new Soil(sensorId,temp,hum,alarmType,createDate);
		return soilService.add(soil);
	}
	/**
	 * 删除土壤表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/delSoil")
	@ResponseBody
	boolean delSoil(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return soilService.delete(map);
	}
	/**
	 * 查询土壤表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/querySoils")
	@ResponseBody
	JSONObject querySoils(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Soil> soils = soilService.query(map);
		json.put("airs", JSONArray.fromObject(soils));
		return json;
	}
	
	/**
	 * 添加空气表数据
	 * @param temp
	 * @param hum
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addAir")
	@ResponseBody
	boolean addAir(@RequestParam("temp") double temp,
			@RequestParam("hum") double hum,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "正常";
		if (temp <= 20) {
			alarmType = "空气温度过低";
		} else if (temp >= 40) {
			alarmType = "空气温度过高";
		}
		Air air = new Air(temp,hum,alarmType,createDate,sensorId);
		return airService.add(air);
	}
	/**
	 * 删除空气表数据
	 * @param createDate
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="delAir")
	@ResponseBody
	boolean delAir(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		return airService.delete(map);
	}
	/**
	 * 查询空气表
	 * @param createDate
	 * @param sensorId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="queryAirs")
	@ResponseBody
	JSONObject queryAirs(@RequestParam("createDate") String createDate,
			@RequestParam("sensorId") int sensorId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(sensorId != -1) {
			map.put("sensorId", sensorId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Air> airs = airService.query(map);
		json.put("airs", JSONArray.fromObject(airs));
		return json;
	}
}

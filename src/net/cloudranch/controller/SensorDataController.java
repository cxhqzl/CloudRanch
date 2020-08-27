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
				case "������ʪ��":
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
				case "������ʪ��":
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
				case "����ǿ��":
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
				case "���շ���":
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
				case "���ٷ���":
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
				case "¶���¶�":
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
				case "������":
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
	 * ����Ա��ȡ����������
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
				case "������ʪ��":
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
				case "������ʪ��":
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
				case "����ǿ��":
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
				case "���շ���":
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
				case "���ٷ���":
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
				case "¶���¶�":
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
				case "������":
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
	 * ��ѯ������ ����
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
				case "������ʪ��":
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
				case "������ʪ��":
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
				case "����ǿ��":
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
				case "���շ���":
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
				case "���ٷ���":
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
				case "¶���¶�":
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
				case "������":
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
	 * ���¶���¶�
	 * @param temp
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addDew")
	@ResponseBody
	boolean addDew(@RequestParam("temp") double temp,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		Dew dew = new Dew( temp, "����", createDate,sensorId);
		return dewService.add(dew);
	}
	/**
	 * ɾ��¶���¶ȱ�����
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
	 * ��ѯ¶���¶ȱ�
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
	 * ��ӽ���
	 * @param rainFall
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addRainFall")
	@ResponseBody
	boolean addRainFall(@RequestParam("rainFall") double rainFall,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "����";   
		if (rainFall >= 150) {
			alarmType = "����������";
		}
		RainFall rf = new RainFall(rainFall, alarmType, createDate,sensorId);
		return rainFallService.add(rf);
	}
	/**
	 * ɾ�����������
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
	 * ��ѯ�����
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
	 * ��ӹ�ǿ����
	 * @param intensity
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addRadiation")
	@ResponseBody
	boolean addRadiation(@RequestParam("intensity") double intensity,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "����";
		if (intensity <= 1) {
			alarmType = "���ս���";
		} else if (intensity >= 150000) {
			alarmType = "���չ�ǿ";
		}
		Radiation radiation = new Radiation(sensorId, intensity, alarmType, createDate);
		return radiationService.add(radiation);
	}
	/**
	 * ɾ��radiation������
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
	 * ��ѯradiation��
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
	 * ��ӹ������
	 * @param intensity
	 * @param sensorId
	 * @return
	 */
	@RequestMapping(value="/addPar")
	@ResponseBody
	boolean addPar(@RequestParam("intensity") double intensity,
			@RequestParam("sensorId") int sensorId) {
		String createDate = BasicUtils.getDatetime();
		String alarmType = "����";
		if (intensity <= 0) {
			alarmType = "��Ч��Ϸ������";
		} else if (intensity >= 1) {
			alarmType = "��Ч��Ϸ����ǿ";
		}
		Par par = new Par(sensorId, intensity, alarmType, createDate);
		return parService.add(par);
	}
	/**
	 * ɾ��par������
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
	 * ��ѯpar��
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
	 * ���wind������
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
		String alarmType = "����";
		if (speed <= 10)
			alarmType = "���ٹ���";
		if (speed >= 30)
			alarmType = "���ٹ���";
		Wind wind = new Wind(sensorId, speed, direction, alarmType, createDate);
		return windService.add(wind);
	}
	/**
	 * ɾ��wind������
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
	 * ��ѯwind��
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
	 * �������������
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
		String alarmType = "����";
		if (temp <= 20) {
			alarmType = "�����¶ȹ���";
		} else if (temp >= 40) {
			alarmType = "�����¶ȹ���";
		}
		Soil soil = new Soil(sensorId,temp,hum,alarmType,createDate);
		return soilService.add(soil);
	}
	/**
	 * ɾ������������
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
	 * ��ѯ������
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
	 * ��ӿ���������
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
		String alarmType = "����";
		if (temp <= 20) {
			alarmType = "�����¶ȹ���";
		} else if (temp >= 40) {
			alarmType = "�����¶ȹ���";
		}
		Air air = new Air(temp,hum,alarmType,createDate,sensorId);
		return airService.add(air);
	}
	/**
	 * ɾ������������
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
	 * ��ѯ������
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

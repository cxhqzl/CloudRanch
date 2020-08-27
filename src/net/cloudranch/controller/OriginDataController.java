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

import net.cloudranch.domain.Grow;
import net.cloudranch.domain.Harvest;
import net.cloudranch.domain.Irrigate;
import net.cloudranch.domain.Pesticide;
import net.cloudranch.domain.Place;
import net.cloudranch.domain.Plant;
import net.cloudranch.domain.SpreadMan;
import net.cloudranch.domain.Wog;
import net.cloudranch.service.GrowService;
import net.cloudranch.service.HarvestService;
import net.cloudranch.service.IrrigateService;
import net.cloudranch.service.PesticideService;
import net.cloudranch.service.PlaceService;
import net.cloudranch.service.PlantService;
import net.cloudranch.service.SpreadManService;
import net.cloudranch.service.WogService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class OriginDataController {
	@Autowired
	@Qualifier("plantService")
	private PlantService plantService;
	@Autowired
	@Qualifier("placeService")
	private PlaceService placeService;
	@Autowired
	@Qualifier("spreadManService")
	private SpreadManService spreadManService;
	@Autowired
	@Qualifier("irrigateService")
	private IrrigateService irrigateService;
	@Autowired
	@Qualifier("pesticideService")
	private PesticideService pesticideService;
	@Autowired
	@Qualifier("wogService")
	private WogService wogService;
	@Autowired
	@Qualifier("growService")
	private GrowService growService;
	@Autowired
	@Qualifier("harvestService")
	private HarvestService harvestService;
	
	/**
	 * ��ѯ��Դ����
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/queryOriginDatas")
	@ResponseBody
	JSONObject queryOriginDatas(@RequestParam("cropId") String cropId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cropId", cropId);
		//ʩ������
		List<SpreadMan> shs = spreadManService.querySpreadMans(map);
		json.put("spreadMans", JSONArray.fromObject(shs));
		if(shs.size() > 0) {
			json.put("spreadMan", shs.get(0));
		}else {
			json.put("spreadMan", null);
		}
		//�������
		List<Irrigate> is = irrigateService.queryIrrigates(map);
		json.put("irrigates", JSONArray.fromObject(is));
		if(is.size() > 0) {
			json.put("irrigate", is.get(0));
		}else {
			json.put("irrigate", null);
		}
		//ũҩ����
		List<Pesticide> ps = pesticideService.queryPesticides(map);
		json.put("pesticides", JSONArray.fromObject(ps));
		if(ps.size() > 0) {
			json.put("pesticide", ps.get(0));
		}else {
			json.put("pesticide", null);
		}
		//���溦����
		List<Wog> ws = wogService.queryWogs(map);
		json.put("wogs", JSONArray.fromObject(ws));
		if(ws.size() > 0) {
			json.put("wog", ws.get(0));
		}else {
			json.put("wog", null);
		}
		//��������
		List<Grow> gs = growService.queryGrows(map);
		json.put("grows", JSONArray.fromObject(gs));
		if(gs.size() > 0) {
			json.put("grow", gs.get(0));
		}else {
			json.put("grow", null);
		}
		//�ճ�����
		List<Harvest> hs = harvestService.queryHarvests(map);
		json.put("harvests", JSONArray.fromObject(hs));
		if(hs.size() > 0) {
			json.put("harvest", hs.get(0));
		}else {
			json.put("harvest", null);
		}
		return json;
	}
	/**
	 * ����ճɼ�¼
	 * @param remarks
	 * @param image
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/addHarvest")
	@ResponseBody
	boolean addHarvest(@RequestParam("remarks") String remarks,
			@RequestParam("image") String image,
			@RequestParam("cropId") String cropId) {
		Harvest harvest = new Harvest(0,remarks,BasicUtils.getDate(),image,cropId);
		return harvestService.addHarvest(harvest);
	}
	/**
	 * ɾ���ճɼ�¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delHarvest")
	@ResponseBody
	boolean delHarvest(@RequestParam("id") int id) {
		return harvestService.delHarvest(id);
	}
	/**
	 * ��ѯ�ճɼ�¼
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/queryHarvests")
	@ResponseBody
	JSONObject queryHarvests(@RequestParam("cropId") String cropId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		List<Harvest> harvests = harvestService.queryHarvests(map);
		map.clear();
		for(Harvest h : harvests) {
			map.put("cropId", h.getCropId());
			Plant p = plantService.queryPlant(map).get(0);
			h.setCropName(p.getCropName());
		}
		JSONObject json = new JSONObject();
		json.put("harvests", JSONArray.fromObject(harvests));
		return json;
	}	
	/**
	 * ��ӳ��Ƽ�¼
	 * @param remarks
	 * @param image
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/addGrow")
	@ResponseBody
	boolean addGrow(@RequestParam("remarks") String remarks,
			@RequestParam("image") String image,
			@RequestParam("cropId") String cropId) {
		Grow grow = new Grow(0,remarks,BasicUtils.getDate(),image,cropId);
		return growService.addGrow(grow);
	}
	/**
	 * ɾ�����Ƽ�¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delGrow")
	@ResponseBody
	boolean delGrow(@RequestParam("id") int id) {
		return growService.delGrow(id);
	}
	/**
	 * ��ѯ���Ƽ�¼
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/queryGrows")
	@ResponseBody
	JSONObject queryGrows(@RequestParam("cropId") String cropId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		List<Grow> grows = growService.queryGrows(map);
		map.clear();
		for(Grow g : grows) {
			map.put("cropId", g.getCropId());
			Plant p = plantService.queryPlant(map).get(0);
			g.setCropName(p.getCropName());
		}
		JSONObject json = new JSONObject();
		json.put("grows", JSONArray.fromObject(grows));
		return json;
	}	
	/**
	 * ��Ӳ��溦��¼
	 * @param remarks
	 * @param image
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/addWog")
	@ResponseBody
	boolean addWog(@RequestParam("remarks") String remarks,
			@RequestParam("image") String image,
			@RequestParam("cropId") String cropId) {
		Wog wog = new Wog(0,remarks,BasicUtils.getDate(),image,cropId);
		return wogService.addWog(wog);
	}
	/**
	 * ɾ�����溦��¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delWog")
	@ResponseBody
	boolean delWog(@RequestParam("id") int id) {
		return wogService.delWog(id);
	}
	/**
	 * ��ѯ���溦��¼
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/queryWogs")
	@ResponseBody
	JSONObject queryWogs(@RequestParam("cropId") String cropId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		List<Wog> wogs = wogService.queryWogs(map);
		map.clear();
		for(Wog w : wogs) {
			map.put("cropId", w.getCropId());
			Plant p = plantService.queryPlant(map).get(0);
			w.setCropName(p.getCropName());
		}
		JSONObject json = new JSONObject();
		json.put("wogs", JSONArray.fromObject(wogs));
		return json;
	}	
	/**
	 * ���ũҩ��¼
	 * @param remarks
	 * @param image
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/addPesticide")
	@ResponseBody
	boolean addPesticide(@RequestParam("remarks") String remarks,
			@RequestParam("image") String image,
			@RequestParam("cropId") String cropId) {
		Pesticide pesticide = new Pesticide(0,remarks,BasicUtils.getDate(),image,cropId);
		return pesticideService.addPesticide(pesticide);
	}
	/**
	 * ɾ��ũҩ��¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delPesticide")
	@ResponseBody
	boolean delPesticide(@RequestParam("id") int id) {
		return pesticideService.delPesticide(id);
	}
	/**
	 * ��ѯũҩ��¼
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/queryPesticides")
	@ResponseBody
	JSONObject queryPesticides(@RequestParam("cropId") String cropId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		List<Pesticide> pesticides = pesticideService.queryPesticides(map);
		map.clear();
		for(Pesticide p : pesticides) {
			map.put("cropId", p.getCropId());
			Plant plant = plantService.queryPlant(map).get(0);
			p.setCropName(plant.getCropName());
		}
		JSONObject json = new JSONObject();
		json.put("pesticides", JSONArray.fromObject(pesticides));
		return json;
	}	
	/**
	 * ��ӹ�ȼ�¼
	 * @param remarks
	 * @param image
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/addIrrigate")
	@ResponseBody
	boolean addIrrigate(@RequestParam("remarks") String remarks,
			@RequestParam("image") String image,
			@RequestParam("cropId") String cropId) {
		Irrigate irrigate = new Irrigate(0,remarks,BasicUtils.getDate(),image,cropId);
		return irrigateService.addIrrigate(irrigate);
	}
	/**
	 * ɾ����ȼ�¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delIrrigate")
	@ResponseBody
	boolean delIrrigate(@RequestParam("id") int id) {
		return irrigateService.delIrrigate(id);
	}
	/**
	 * ��ѯ��ȼ�¼
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/queryIrrigates")
	@ResponseBody
	JSONObject queryIrrigates(@RequestParam("cropId") String cropId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		List<Irrigate> irrigates = irrigateService.queryIrrigates(map);
		map.clear();
		for(Irrigate i : irrigates) {
			map.put("cropId", i.getCropId());
			Plant p = plantService.queryPlant(map).get(0);
			i.setCropName(p.getCropName());
		}
		JSONObject json = new JSONObject();
		json.put("irrigates", JSONArray.fromObject(irrigates));
		return json;
	}	
	/**
	 * ���ʩ�ʼ�¼
	 * @param remarks
	 * @param image
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/addSpreadMan")
	@ResponseBody
	boolean addSpreadMan(@RequestParam("remarks") String remarks,
			@RequestParam("image") String image,
			@RequestParam("cropId") String cropId) {
		SpreadMan sm = new SpreadMan(0,remarks,BasicUtils.getDate(),image,cropId);
		return spreadManService.addSpreadMan(sm);
	}
	/**
	 * ɾ��ʩ�ʼ�¼
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delSpreadMan")
	@ResponseBody
	boolean delSpreadMan(@RequestParam("id") int id) {
		return spreadManService.delSpreadMan(id);
	}
	/**
	 * ��ѯʩ�ʼ�¼
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/querySpreadMans")
	@ResponseBody
	JSONObject querySpreadMans(@RequestParam("cropId") String cropId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		List<SpreadMan> sms = spreadManService.querySpreadMans(map);
		map.clear();
		for(SpreadMan sm : sms) {
			map.put("cropId", sm.getCropId());
			Plant p = plantService.queryPlant(map).get(0);
			sm.setCropName(p.getCropName());
		}
		JSONObject json = new JSONObject();
		json.put("spreadMans", JSONArray.fromObject(sms));
		return json;
	}
	
	/**
	 * �����ֲ����
	 * @param cropName
	 * @param image
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/addPlant")
	@ResponseBody
	boolean addPlant(@RequestParam("cropName") String cropName,
			@RequestParam("image") String image,
			@RequestParam("placeId") int placeId) {
		Plant p = new Plant(BasicUtils.getCropId(), cropName, BasicUtils.getDate(), image, placeId);
		return plantService.addPlant(p);
	}
	/**
	 * ɾ����ֲ����
	 * @param cropId
	 * @return
	 */
	@RequestMapping(value="/delPlant")
	@ResponseBody
	boolean delPlant(@RequestParam("cropId") String cropId) {
		return plantService.delPlant(cropId);
	}
	/**
	 * ��ѯ��ֲ��
	 * @param createDate
	 * @param cropId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryPlants")
	@ResponseBody
	JSONObject queryPlants(@RequestParam("createDate") String createDate,
			@RequestParam("cropId") String cropId,
			@RequestParam("placeId") int placeId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!createDate.equals("")) {
			map.put("createDate", createDate);
		}
		if(!cropId.equals("")) {
			map.put("cropId", cropId);
		}
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(limit != -1) {
			map.put("limit", limit);
		}
		if(pageNumber > 0) {
			int beginIndex = (pageNumber - 1) * limit;
			map.put("beginIndex", beginIndex);
		}
		List<Plant> plants = plantService.queryPlant(map);
		map.clear();
		for(Plant p : plants) {
			map.put("placeId", p.getPlaceId());
			Place place = placeService.queryPlaces(map).get(0);
			p.setPlaceName(place.getPlaceName());
		}
		json.put("plants", JSONArray.fromObject(plants));
		return json;
	}
}

package net.cloudranch.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cloudranch.domain.SiteImage;
import net.cloudranch.service.SiteImageService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class SiteImageController {
	
	@Autowired
	@Qualifier("siteImageService")
	private SiteImageService siteImageService;
	
	/**
	 * ���վ��ͼƬ
	 * @param image
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/addSiteImage")
	@ResponseBody
	boolean addSiteImage(@RequestParam("image") String image,
			@RequestParam("siteId") int siteId) {
		SiteImage siteImage = new SiteImage(0,image,BasicUtils.getDatetime(),siteId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		List<SiteImage> sis = siteImageService.querySiteImages(map);
		if(sis.size() == 9) {
			String path = BasicUtils.getStoragePath("siteImage");
			File f = new File(path + "\\" + image);
			if(f.exists()) {
				f.delete();
			}
			return false;
		}else {
			return siteImageService.addSiteImage(siteImage);
		}
	}
	/**
	 * ɾ��վ��ͼƬ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delSiteImage")
	@ResponseBody
	boolean delSiteImage(@RequestParam("id") int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<SiteImage> sis = siteImageService.querySiteImages(map);
		if(sis.size() > 0) {
			String fileName = sis.get(0).getImage();
			String path = BasicUtils.getStoragePath("siteImage");
			File f = new File(path + "\\" + fileName);
			if(f.exists()) {
				f.delete();
			}
		}
		return siteImageService.delSiteImage(id);
	}
	/**
	 * �޸�վ��ͼƬ
	 * @param id
	 * @param image
	 * @return
	 */
	@RequestMapping(value="/modifiySiteImage")
	@ResponseBody
	boolean modifiySiteImage(@RequestParam("id") int id,
			@RequestParam("image") String image) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("image", image);
		return siteImageService.modifiySiteImage(map);
	}
	/**
	 * ��ѯվ��ͼƬ��Ϣ
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/querySiteImages")
	@ResponseBody
	JSONObject querySiteImage(@RequestParam("siteId") int siteId) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		List<SiteImage> siteImages = siteImageService.querySiteImages(map);
		json.put("siteImages", JSONArray.fromObject(siteImages));
		return json;
	}
}

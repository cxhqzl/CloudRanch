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

import net.cloudranch.domain.Node;
import net.cloudranch.service.NodeService;
import net.cloudranch.utils.BasicUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class NodeController {
	
	@Autowired
	@Qualifier("nodeService")
	private NodeService nodeService;
	
	/**
	 * 添加节点
	 * @param nodeName
	 * @param mac
	 * @param type
	 * @param interval
	 * @param isReport
	 * @param status
	 * @param battery
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/addNode")
	@ResponseBody
	boolean addNode(@RequestParam("nodeName") String nodeName,
			@RequestParam("mac") String mac,
			@RequestParam("type") String type,
			@RequestParam("interval") int interval,
			@RequestParam("isReport") int isReport,
			@RequestParam("status") String status,
			@RequestParam("battery") double battery,
			@RequestParam("placeId") int placeId) {
		Node node = new Node(0, nodeName, mac, type, interval, isReport, status, battery, BasicUtils.getDatetime(), placeId);
		return nodeService.addNode(node);
	}
	/**
	 * 删除节点
	 * @param nodeId
	 * @return
	 */
	@RequestMapping(value="/delNode")
	@ResponseBody
	boolean delNode(@RequestParam("nodeId") int nodeId) {
		return nodeService.delNode(nodeId);
	}
	/**
	 * 修改节点
	 * @param nodeName
	 * @param mac
	 * @param type
	 * @param interval
	 * @param isReport
	 * @param status
	 * @param battery
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value="/modifiNode")
	@ResponseBody
	boolean modifiNode(@RequestParam("nodeName") String nodeName,
			@RequestParam("mac") String mac,
			@RequestParam("type") String type,
			@RequestParam("interval") int interval,
			@RequestParam("isReport") int isReport,
			@RequestParam("status") String status,
			@RequestParam("battery") double battery,
			@RequestParam("placeId") int placeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!nodeName.equals("")) {
			map.put("nodeName", nodeName);
		}
		if(!mac.equals("")) {
			map.put("mac", mac);
		}
		if(!type.equals("")) {
			map.put("type", type);
		}
		if(interval != -1) {
			map.put("interval", interval);
		}
		if(isReport != -1) {
			map.put("isReport", isReport);
		}
		if(!status.equals("")) {
			map.put("status", status);
		}
		if(battery != -1) {
			map.put("battery", battery);
		}
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		return nodeService.modifiNode(map);
	}
	/**
	 * 查询节点信息
	 * @param placeId
	 * @param nodeId
	 * @param limit
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="/queryNodes")
	@ResponseBody
	JSONObject queryNodes(@RequestParam("placeId") int placeId,
			@RequestParam("nodeId") int nodeId,
			@RequestParam("limit") int limit,
			@RequestParam("pageNumber") int pageNumber) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(placeId != -1) {
			map.put("placeId", placeId);
		}
		if(nodeId != -1) {
			map.put("nodeId", nodeId);
		}
		if(limit != -1 && pageNumber > 0) {
			map.put("limit", limit);
			map.put("pageNumber", (pageNumber - 1) * limit);
		}
		List<Node> nodes = nodeService.queryNodes(map);
		json.put("nodes", JSONArray.fromObject(nodes));
		return json;
	}
}

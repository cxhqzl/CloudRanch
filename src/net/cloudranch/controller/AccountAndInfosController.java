package net.cloudranch.controller;

import java.io.File;
import java.io.IOException;
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

import net.cloudranch.domain.Account;
import net.cloudranch.domain.AccountInfo;
import net.cloudranch.domain.LoginLog;
import net.cloudranch.service.AccountInfoService;
import net.cloudranch.service.AccountService;
import net.cloudranch.service.LoginLogService;
import net.cloudranch.utils.BasicUtils;
import net.cloudranch.utils.CreateCodeImage;
import net.cloudranch.utils.MapUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class AccountAndInfosController {
	
	@Autowired
	@Qualifier("accountService")
	private AccountService accountService;
	@Autowired
	@Qualifier("accountInfoService")
	private AccountInfoService accountInfoService;
	@Autowired
	@Qualifier("loginLogService")
	private LoginLogService loginLogService;
	
	
	/**
	 * ��¼
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/Login")
	@ResponseBody
	JSONObject login(HttpServletRequest request,
			@RequestParam("account") String account,
			@RequestParam("password") String password) {
		int type = 2;
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		boolean flag = accountService.checkAccount(map);
		if(!flag) {
			type = 1;//�˺Ų�����
		}else {
			map.put("password", password);
			flag = accountService.checkPassword(map);
			if(!flag) {
				type = 2;//�������
			}else {
				type = 0;//��¼�ɹ�
				int role = accountService.queryAccountRole(map);
				json.put("role", role);
				request.getSession().setAttribute("account", account);
				map.clear();
				map.put("account", account);
				map.put("type", 2);
				LoginLog log = loginLogService.queryLog(map);
				log.setType(1);
				loginLogService.modifiLog(log);
				String IP = BasicUtils.getLoginIP(request);
				String address = MapUtils.getAddressByIP(IP);
				map.clear();
				map.put("account", account);
				List<AccountInfo> ais = accountInfoService.query(map);
				String oldAddress = "";
				String oldPro = ais.get(0).getProvince();
				if(oldPro.equals("����") || oldPro.equals("�Ϻ�") || oldPro.equals("���") || oldPro.equals("����")) {
					oldAddress = oldPro + "��";
				}else {
					oldAddress = oldPro + ais.get(0).getCity();
				}
				/*if(!oldAddress.equals(address)) {
					BasicUtils.sendEmailWarning(ais.get(0).getEmail(), account, IP, address);
				}*/
				LoginLog log1 = new LoginLog(account, address, IP, BasicUtils.getDatetime(),2);
				loginLogService.modifiLog(log1);
			}
		}
		json.put("type", type);
		json.put("url", BasicUtils.getURL(request));
		return json;
	}
	/**
	 * ��ȡ�ϴε�¼��־
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getLoginLog")
	@ResponseBody
	JSONObject getLoginLog(@RequestParam("account") String account) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("type", 1);
		LoginLog log = loginLogService.queryLog(map);
		json.put("loginLog", JSONObject.fromObject(log));
		return json;
	}
	/**
	 * ��ȡ��������
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getAccountData")
	@ResponseBody
	JSONObject getAccountData(@RequestParam("account") String account) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<AccountInfo> ais = accountInfoService.query(map);
		json.put("accountInfo", JSONObject.fromObject(ais.get(0)));
		return json;
	}
	/**
	 * �ǳ�
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	boolean logout(HttpServletRequest request) {
		boolean flag = false;
		try {
			request.getSession().invalidate();//���ȫ��session
			flag = true;
		}catch(Exception e) {
			
		}
		return flag;
	}
	/**
	 * ע���˺�
	 * @param account
	 * @param password
	 * @param userName
	 * @param sex
	 * @param age
	 * @param province
	 * @param city
	 * @param county
	 * @param location
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	JSONObject register(@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("userName") String userName,
			@RequestParam("image") String image,
			@RequestParam("sex") String sex,
			@RequestParam("age") int age,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("county") String county,
			@RequestParam("location") String location,
			@RequestParam("phone") String phone,
			@RequestParam("code") String code,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("account", account);
		if(accountService.checkAccount(map1)) {
			json.put("type", 2);//�˺��Ѵ���
			return json;
		}
		if(!code.equals(request.getSession().getAttribute("code"))) {
			json.put("type", 1);//��֤�����
			return json;
		}
		Account a = new Account(account,password,0);
		boolean flag = accountService.register(a);
		if(image == null || image.equals("")) {
			image = "default.jpg";
		}
		if(flag) {
			Map<String,Double> map = MapUtils.getLngAndLat(province+city+county+location);
			AccountInfo accountInfo = new AccountInfo(account,userName,image,sex,age,province,city,county,location,map.get("lng"),map.get("lat"),phone,account,BasicUtils.getDate());
			accountInfoService.add(accountInfo);
			json.put("type", 0);//ע��ɹ�
			String IP = BasicUtils.getLoginIP(request);
			String address = MapUtils.getAddressByIP(IP);
			LoginLog log = new LoginLog(account, address, IP, BasicUtils.getDatetime(),1);
			loginLogService.addLog(log);
			log.setType(2);
			loginLogService.addLog(log);
		}else {
			json.put("type", 2);//ע��ʧ��
		}
		return json;
	}
	/**
	 * ����Ա����˺�
	 * @param account
	 * @param password
	 * @param userName
	 * @param image
	 * @param sex
	 * @param age
	 * @param province
	 * @param city
	 * @param county
	 * @param location
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adminAddAccount")
	@ResponseBody
	JSONObject adminAddAccount(@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("userName") String userName,
			@RequestParam("image") String image,
			@RequestParam("sex") String sex,
			@RequestParam("age") int age,
			@RequestParam("province") String province,
			@RequestParam("city") String city,
			@RequestParam("county") String county,
			@RequestParam("location") String location,
			@RequestParam("phone") String phone,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("account", account);
		if(accountService.checkAccount(map1)) {
			json.put("type", 2);//�˺��Ѵ���
			return json;
		}
		Account a = new Account(account,password,0);
		boolean flag = accountService.register(a);
		if(image == null || image.equals("")) {
			image = "default.jpg";
		}
		if(flag) {
			Map<String,Double> map = MapUtils.getLngAndLat(province+city+county+location);
			AccountInfo accountInfo = new AccountInfo(account,userName,image,sex,age,province,city,county,location,map.get("lng"),map.get("lat"),phone,account,BasicUtils.getDate());
			accountInfoService.add(accountInfo);
			json.put("type", 0);//ע��ɹ�
			String IP = BasicUtils.getLoginIP(request);
			String address = MapUtils.getAddressByIP(IP);
			LoginLog log = new LoginLog(account, address, IP, BasicUtils.getDatetime(),1);
			loginLogService.addLog(log);
			log.setType(2);
			loginLogService.addLog(log);
		}else {
			json.put("type", 2);//ע��ʧ��
		}
		return json;
	}
	/**
	 * ��ȡע����֤��
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/getRegisterCode")
	@ResponseBody
	JSONObject getRegisterCode(@RequestParam("email") String email,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("account", email);
		if(accountService.checkAccount(map1)) {
			json.put("type", 1);//�˺��Ѵ���
			return json;
		}
		String code = BasicUtils.getCode(6);
		request.getSession().setAttribute("code", code);
		json.put("flag", BasicUtils.sendEmailCode(email, code));
		return json;
	}
	/**
	 * ��ȡͼƬ��֤��
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/getCodeImage")
	@ResponseBody
	JSONObject getCodeImage() throws IOException {
		String path = BasicUtils.getStoragePath("codeImage");
		File file = new File(path);
		if(file.exists()) {
			File[] fs = file.listFiles();
			for(File f : fs) {
				if(f.isFile()) {
					f.delete();
				}
			}
		}
		JSONObject json = new JSONObject();
		Map<String,String> map = CreateCodeImage.createCodeImage();
		json.put("codeName", map.get("fileName"));
		json.put("code", map.get("code"));
		return json;
	}
	/**
	 * �޸��˺�Ȩ��
	 * @param account
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/modifiAccountRole")
	@ResponseBody
	boolean modifiAccountRole(@RequestParam("account") String account,
			@RequestParam("role") int role) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("role", role);
		return accountService.modifiedAccount(map);
	}
	@RequestMapping(value="/queryAccountInfos")
	@ResponseBody
	JSONObject queryAccountInfos(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		List<AccountInfo> as = accountInfoService.query(map);
		for(AccountInfo a : as) {
			map.put("account", a.getAccount());
			a.setRole(accountService.queryAccountRole(map));
		}
		JSONObject json = new JSONObject();
		json.put("accountInfos", JSONArray.fromObject(as));
		return json;
	}
	/**
	 * ����Ա��ȡ�˺���Ϣ
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/queryAccounts")
	@ResponseBody
	JSONObject queryAccounts(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("limit") int limit,
			@RequestParam("role") int role) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<AccountInfo> as1 = accountInfoService.query(map);
		JSONArray jsonA1 = new JSONArray();
		for(AccountInfo a : as1) {
			if(a.getAccount().equals("1")) {
				as1.remove(a);
				break;
			}
		}
		for(AccountInfo a : as1) {
			map.put("account", a.getAccount());
			int role1 = accountService.queryAccountRole(map);
			a.setRole(role1);
			if(role == 3) {
				jsonA1.add(a);
			}else {
				if(role == role1) {
					jsonA1.add(a);
				}
			}
		}
		int size = jsonA1.size();
		map.clear();
		if(pageNumber != -1 && limit != -1){
			int beginIndex = (pageNumber - 1) * limit;
			if(beginIndex == 0) {
				limit++;
			}
			map.put("beginIndex", beginIndex);
			map.put("limit", limit);
		}
		List<AccountInfo> as = accountInfoService.query(map);
		for(AccountInfo a : as) {
			if(a.getAccount().equals("1")) {
				as.remove(a);
				break;
			}
		}
		JSONArray jsonA = new JSONArray();
		for(AccountInfo a : as) {
			map.put("account", a.getAccount());
			int role1 = accountService.queryAccountRole(map);
			a.setRole(role1);
			if(role == 3) {
				jsonA.add(a);
			}else {
				if(role == role1) {
					jsonA.add(a);
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("accounts", jsonA);
		json.put("size", size);
		json.put("index", pageNumber);
		return json;
	}
	/**
	 * ɾ���˺�
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/delAccount")
	@ResponseBody
	boolean delAccount(@RequestParam("account") String account) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		return accountService.del(account);
	}
}


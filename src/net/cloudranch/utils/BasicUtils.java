package net.cloudranch.utils;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

public class BasicUtils {
	/**
	 * 获取服务器url前缀部分 
	 * @param request
	 * @return http://127.0.0.1/项目/
	 */
	public static String getURL(HttpServletRequest request) {
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		return url+"/";
	}
	/**
	 * 生成羊id
	 * @param existSheepId 当天已注册的羊id
	 * @param number 需要生成id数量
	 * @return
	 */
	public static List<String> produceSheepId(Map<String, Object> existSheepId,int number){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String date = sd.format(new Date());
		List<String> sheepIds = new ArrayList<String>();
		while(sheepIds.size()<number) {
			String num = String.valueOf((int) Math.ceil(Math.random()*10000));
			String last = "";
			switch(num.length()) {
				case 1:
					last = "000" + num;
					break;
				case 2:
					last = "00" + num;
					break;
				case 3:
					last = "0" + num;
					break;
				case 4:
					last = num;
					break;
			}
			String id = date + last;
			if(existSheepId == null) {
				existSheepId = new HashMap<String,Object>();
			}
			if(existSheepId.containsKey(id)) {
				continue;
			}
			sheepIds.add(id);
			existSheepId.put(id, 0);
		}
		return sheepIds;
	}
	/**
	 * 生成块ID
	 * @param sheepId
	 * @param unitNumber
	 * @return
	 */
	public static List<String> produceUnitId(String sheepId,int unitNumber,Map<String,Object> oldUnitIds){
		List<String> unitIds = new ArrayList<String>();
		if(oldUnitIds == null) {
			for(int i=0;i<unitNumber;i++) {
				if(String.valueOf(i).length()==1) {
					unitIds.add(sheepId + "0" + i);
				}else if(String.valueOf(i).length()==2) {
					unitIds.add(sheepId + i);
				}
			}
			return unitIds;
		}else {
			for(int i=0;i<unitNumber;i++) {
				if(String.valueOf(i).length()==1) {
					if(oldUnitIds.containsKey(sheepId + "0" + i)) {
						unitNumber++;
					}else {
						unitIds.add(sheepId + "0" + i);
					}
				}else if(String.valueOf(i).length()==2) {
					if(oldUnitIds.containsKey(sheepId + i)) {
						unitNumber++;
					}else {
						unitIds.add(sheepId + i);
					}
				}
			}
			return unitIds;
		}
	}
	/**
	 * 生成n位随机数
	 * @param count 生成验随机数位数
	 * @return
	 */
	public static String getCode(int count) {
		String str = "";
		for(int i=0;i<count;i++) {
			str += (int) Math.floor(Math.random() * 10);
		}
		return str;
	}
	/**
	 * 使用邮箱发送验证码
	 * @param email 接收邮箱
	 * @param code 验证码
	 * @return
	 */
	public static boolean sendEmailCode(String email,String code) {
		try {
			HtmlEmail Email = new HtmlEmail();
			Email.setHostName("smtp.163.com");
			Email.setCharset("UTF-8");
			Email.addTo(email);// 收件地址
			//设置邮件标题
			Email.setFrom("18555502667@163.com", "云农场系统");
			//设置发送邮箱，授权码
			Email.setAuthentication("18555502667@163.com", "cxh123456");
			//设置邮件内容
			Email.setSubject("欢迎使用云农场");
			Email.setMsg("您的注册账号验证码是:" + code);

			Email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 发送异地登录警告
	 * @param email
	 * @param account
	 * @param IP
	 * @param address
	 * @return
	 */
	public static boolean sendEmailWarning(String email,String account,String IP,String address) {
		try {
			HtmlEmail Email = new HtmlEmail();
			Email.setHostName("smtp.163.com");
			Email.setCharset("UTF-8");
			Email.addTo(email);// 收件地址
			//设置邮件标题
			Email.setFrom("18555502667@163.com", "云农场系统");
			//设置发送邮箱，授权码
			Email.setAuthentication("18555502667@163.com", "cxh123456");
			//设置邮件内容
			Email.setSubject("异地登录警告");
			String str = "账号：" + account + "\n";
			str += "登录时间：" + getDatetime() + "\n";
			str += "登录IP：" + IP + "\n";
			str += "登录地址：" + address + "\n";
			str += "若非本人操作，请及时前往修改密码 https://www.agribigdata.net/CloudRanch";
			Email.setMsg(str);

			Email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 获取一个文件存储路径
	 * @param name 相对根目录的路径名
	 * @return
	 */
	public static String getStoragePath(String name) {
		String path = "C:\\images\\CloudRanchFileStorage\\"+name;
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
	/**
	 * SHA加密运算
	 * @param text
	 * @return
	 */
	public static String SHA(String text) {
		byte[] secretBytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(text.getBytes());
			secretBytes = md.digest();
		}catch(Exception e) {
			
		}
		//将加密结果转换为16进制数字
		String md5code = new BigInteger(1, secretBytes).toString(16);
		//如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) { 
			md5code = "0" + md5code; 
		}
		return md5code;
	}
	/**
	 * 获取几天前日期
	 * @param num
	 * @return
	 */
	public static String getLastDate(int num) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String date = sd.format(new Date(d.getTime() - num * 24 * 60 * 60 * 1000));
		return date;
	}
	/**
	 * 获取当前日期和时间
	 * @return
	 */
	public static String getDatetime() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sd.format(new Date());
		return date;
	}
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String date = sd.format(new Date());
		return date;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getTime() {
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
		String date = sd.format(new Date());
		return date;
	}
	/**
	 * 获取一个随机字符串
	 * @param number 字符串长度
	 * @return
	 */
	public static String getRandomString(int number) {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM";
		String str = "";
		for(int i=0;i<number;i++) {
			int rand = (int) (Math.random() * 62);
			str += chars.charAt(rand);
		}
		return str;
	}
	/**
	 * 创建图片验证码
	 * @return
	 */
	public static Map<String,String> createImageCode() {
		Map<String,String> map = new HashMap<String,String>();
		//定义图形验证码的长、宽、验证码个数、干扰线数
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(76, 40,4,100);
		String path = BasicUtils.getStoragePath("codeImage");
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		String fileName = "code" + new Date().getTime() + ".png";
		map.put("fileName", fileName);
		//图形验证码写出，可以写出到文件，也可以写出到流
		lineCaptcha.write(path+"\\"+fileName);
		//输出code
		map.put("code", lineCaptcha.getCode());
		//验证图形验证码的有效性，返回boolean值
		lineCaptcha.verify("1234");
		
		return map;
	}
	/**
	 * 获取客户端登录IP
	 * @param request
	 * @return
	 */
	public static String getLoginIP(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	/**
	 * 生成种植作物ID
	 * @return
	 */
	public static String getCropId() {
		Date d = new Date();
		String str1 = "";
		String str2 = "";
		for(int i=0;i<5;i++) {
			str1 += (int) Math.floor(Math.random() * 10);
			str2 += (int) Math.floor(Math.random() * 10);
		}
		String res = str1 + d.getTime() + str2;
		return res;
	}
	public static void main(String[] args) {
		getCropId();
	}
	
	public static void calcArea(String[] lngLats) {
		List<AddressLocation> als = new ArrayList<AddressLocation>();
		for(int i=0;i<lngLats.length;i++) {
			AddressLocation al = new AddressLocation();
			al.x = GetDistance(116.40739,39.904211,Double.parseDouble(lngLats[i].split(",")[0]),39.904211);
			al.y = GetDistance(116.40739,39.904211,116.40739,Double.parseDouble(lngLats[i].split(",")[1]));
			als.add(al);
		}
		double area = 0;
		for(int i=2;i<als.size();i++) {
			area += (als.get(i).x*als.get(i-1).y - als.get(i).y*als.get(i-1).x)/2.0;
		}
		area += (als.get(0).x*als.get(als.size()-1).y - als.get(0).y*als.get(als.size()-1).x)/2.0;
		if(area < 0) {
			area = -area;
		}
		System.out.println(area);
	}
	private static final  double EARTH_RADIUS = 6378137;//赤道半径
	private static double rad(double d){
	    return d * Math.PI / 180.0;
	}
	public static double GetDistance(double lon1,double lat1,double lon2, double lat2) {
	    double radLat1 = rad(lat1);
	    double radLat2 = rad(lat2);
	    double a = radLat1 - radLat2;
	    double b = rad(lon1) - rad(lon2);
	    double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))); 
	    s = s * EARTH_RADIUS;    
	   return s;//单位米
	}
}
class AddressLocation{
	double x;
	double y;
}

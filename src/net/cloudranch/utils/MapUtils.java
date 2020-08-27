package net.cloudranch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MapUtils {
	public static void main(String[] args) {
		String str = "安徽省合肥市长丰县安谷农业有限公司";
		getLngAndLat(str);
	}
	/**
	 * 通过地址获取经纬度
	 * @param address
	 * @return
	 */
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		String url = "https://ditu.google.cn/maps/api/geocode/json?address=" + address
				+ "&key=AIzaSyBpdGZkPePRgztZAXeUxDck2hE0DnWdEV0";
		AsyncHttpClient client = new AsyncHttpClient();
		try {
		Future<Response> f = client.prepareGet(url).execute();
		String json = f.get().getResponseBody("utf8");
		String str1 = JSONObject.fromObject(json).getString("results");
		String str2 = JSONObject.fromObject(JSONArray.fromObject(str1).getString(0)).getString("geometry");
		String str3 = JSONObject.fromObject(str2).getString("location");
		JSONObject json1 = JSONObject.fromObject(str3);
		map.put("lat", Double.parseDouble(json1.get("lat").toString()));
		map.put("lng", Double.parseDouble(json1.get("lng").toString()));
		System.out.println(Double.parseDouble(json1.get("lat").toString()));
		System.out.println(Double.parseDouble(json1.get("lng").toString()));
		}catch(Exception e) {
			
		} 
		return map;
	}
	/**
	 * 解析IP地址
	 * @param IP
	 * @return
	 */
	public static String getAddressByIP(String IP){ 
		String url = "https://api.map.baidu.com/location/ip?ip="+IP+"&ak=mRdryUGzZpUi1IHw2vyaaqtzYv29hIux&coor=bd09ll";
		try {
			String json = loadJSON(url);
			JSONObject obj = JSONObject.fromObject(json);
			JSONObject obj1 = JSONObject.fromObject(obj.get("content"));
			return obj1.get("address").toString();
		}catch(Exception e) {
			return "地址解析错误";
		}
	}
	
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}
}

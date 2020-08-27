package net.cloudranch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class BaiduMapUtils {
	public static void main(String[] args) {
		String str = "����ʡ�Ϸ��г����غϷʰ���ũҵ���޹�˾";
		System.out.println(getLngAndLat(str).get("lat"));
	}
	/**
	 * ͨ����ַ��ȡ��γ��
	 * @param address
	 * @return
	 */
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		String url = "https://api.map.baidu.com/geocoder/v2/?address=" + address
				+ "&output=json&ak=mRdryUGzZpUi1IHw2vyaaqtzYv29hIux";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		if (obj.get("status").toString().equals("0")) {
			double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			map.put("lng", lng);
			map.put("lat", lat);
		} else {
			System.out.println("δ�ҵ���ƥ��ľ�γ�ȣ�");
		}

		return map;
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

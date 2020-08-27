package net.cloudranch.utils;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据经纬度计算面积工具类
 * 
 * @author hwl
 * @version 1.0.0 2018-01-17
 */
public class CalcArea {
	
	// 地球半径 - 米
	// 源码中使用半径为 6367460.0;;
	private static double earthRadiusMeters = 6371000.0;
	private static double metersPerDegree = 2.0 * Math.PI * earthRadiusMeters / 360.0;
	private static double radiansPerDegree = Math.PI / 180.0;
	/**
	 * 带入经纬度计算面积
	 * @param lngLats
	 * @return
	 */
	public static double calcArea(String[] lngLats) {
		List<Point2D.Double> points = new ArrayList<Point2D.Double>();
		for(String s : lngLats) {
			Point2D.Double point = new Point2D.Double(Double.parseDouble(s.split(",")[0]),
					Double.parseDouble(s.split(",")[1]));
			points.add(point);
		}
		double area = getAreaByxy(points);
		return area;
	}
	/***
	 * 平面多边形面积
	 * 
	 * @param points
	 *            Point2D.Double
	 * @return
	 * */
	public static double getAreaByxy(List<Point2D.Double> points) {
		double a = 0.0;
		if(points.size() > 2){
			for (int i = 0; i < points.size(); ++i) {
				int j = (i + 1) % points.size();
				double xi = points.get(i).x * metersPerDegree
						* Math.cos(points.get(i).y * radiansPerDegree);
				double yi = points.get(i).y * metersPerDegree;
				double xj = points.get(j).x * metersPerDegree
						* Math.cos(points.get(j).y * radiansPerDegree);
				double yj = points.get(j).y * metersPerDegree;
				a += xi * yj - xj * yi;
			}
		}
		a = BigDecimal.valueOf(Math.abs(a / 2.0)).setScale(1,BigDecimal.ROUND_DOWN).doubleValue();
		return a;
	}
}

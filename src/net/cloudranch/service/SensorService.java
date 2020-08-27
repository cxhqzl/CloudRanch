package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Sensor;

public interface SensorService {
	
	/**添加节点*/
	public boolean addSensor(Sensor sensor);
	
	/**删除*/
	public boolean delSensor(int sensorId);
	
	/**修改节点*/
	public boolean modifiSensor(Map<String,Object> map);
	
	/**查询*/
	public List<Sensor> querySensors(Map<String,Object> map);
}

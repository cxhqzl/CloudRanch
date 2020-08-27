package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Sensor;

public interface SensorService {
	
	/**��ӽڵ�*/
	public boolean addSensor(Sensor sensor);
	
	/**ɾ��*/
	public boolean delSensor(int sensorId);
	
	/**�޸Ľڵ�*/
	public boolean modifiSensor(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Sensor> querySensors(Map<String,Object> map);
}

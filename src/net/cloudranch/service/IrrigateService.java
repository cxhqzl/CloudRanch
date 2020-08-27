package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Irrigate;

public interface IrrigateService {
	
	/**���*/
	public boolean addIrrigate(Irrigate irrigate);
	
	/**ɾ��*/
	public boolean delIrrigate(int id);
	
	/**�޸�*/
	public boolean modifiedIrrigate(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Irrigate> queryIrrigates(Map<String,Object> map);
	
}

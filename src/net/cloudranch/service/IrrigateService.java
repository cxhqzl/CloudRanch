package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Irrigate;

public interface IrrigateService {
	
	/**Ìí¼Ó*/
	public boolean addIrrigate(Irrigate irrigate);
	
	/**É¾³ı*/
	public boolean delIrrigate(int id);
	
	/**ĞŞ¸Ä*/
	public boolean modifiedIrrigate(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Irrigate> queryIrrigates(Map<String,Object> map);
	
}

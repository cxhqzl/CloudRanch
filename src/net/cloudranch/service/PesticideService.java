package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Pesticide;

public interface PesticideService {
	
	/**Ìí¼Ó*/
	public boolean addPesticide(Pesticide pesticide);
	
	/**É¾³ı*/
	public boolean delPesticide(int id);
	
	/**ĞŞ¸Ä*/
	public boolean modifiedPesticide(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Pesticide> queryPesticides(Map<String,Object> map);
	
}

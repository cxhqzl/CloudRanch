package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.DividerPlace;

public interface DividerPlaceService {
	
	/**Ìí¼Ó*/
	public boolean addDividerPlace(DividerPlace dividerPlace);
	
	/**É¾³ý*/
	public boolean delDividerPlace(int dividerId);
	
	/**ÐÞ¸Ä*/
	public boolean modifiDividerPlace(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<DividerPlace> queryDividerPlaces(Map<String,Object> map);
}

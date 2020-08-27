package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.DividerPlace;

public interface DividerPlaceService {
	
	/**���*/
	public boolean addDividerPlace(DividerPlace dividerPlace);
	
	/**ɾ��*/
	public boolean delDividerPlace(int dividerId);
	
	/**�޸�*/
	public boolean modifiDividerPlace(Map<String,Object> map);
	
	/**��ѯ*/
	public List<DividerPlace> queryDividerPlaces(Map<String,Object> map);
}

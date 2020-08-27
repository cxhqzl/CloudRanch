package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Place;

public interface PlaceService {

	/**��ӵؿ�*/
	public boolean addPlace(Place place);
	
	/**ɾ��*/
	public boolean delPlace(int placeId);
	
	/**�޸�*/
	public boolean modifiPlace(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Place> queryPlaces(Map<String,Object> map);
}

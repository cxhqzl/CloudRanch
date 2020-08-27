package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Place;

public interface PlaceService {

	/**添加地块*/
	public boolean addPlace(Place place);
	
	/**删除*/
	public boolean delPlace(int placeId);
	
	/**修改*/
	public boolean modifiPlace(Map<String,Object> map);
	
	/**查询*/
	public List<Place> queryPlaces(Map<String,Object> map);
}

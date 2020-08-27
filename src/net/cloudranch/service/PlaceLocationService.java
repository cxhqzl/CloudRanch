package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.PlaceLocation;

public interface PlaceLocationService {
	/**添加地块位置记录*/
	public boolean addPlaceLocation(PlaceLocation placeLocation);
	
	/**删除地块位置*/
	public boolean delPlaceLocation(int id);
	
	/**修改地块位置*/
	public boolean modifiyPlaceLocation(Map<String,Object> map);
	
	/**查询地块图片信息*/
	public List<PlaceLocation> queryPlaceLocations(Map<String,Object> map);
	
	/**查询编号是否已存在*/
	public boolean numberExists(Map<String,Object> map);
}

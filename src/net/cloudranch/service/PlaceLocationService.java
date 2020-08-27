package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.PlaceLocation;

public interface PlaceLocationService {
	/**��ӵؿ�λ�ü�¼*/
	public boolean addPlaceLocation(PlaceLocation placeLocation);
	
	/**ɾ���ؿ�λ��*/
	public boolean delPlaceLocation(int id);
	
	/**�޸ĵؿ�λ��*/
	public boolean modifiyPlaceLocation(Map<String,Object> map);
	
	/**��ѯ�ؿ�ͼƬ��Ϣ*/
	public List<PlaceLocation> queryPlaceLocations(Map<String,Object> map);
	
	/**��ѯ����Ƿ��Ѵ���*/
	public boolean numberExists(Map<String,Object> map);
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Wog;

public interface WogService {
	
	/**���*/
	public boolean addWog(Wog wog);
	
	/**ɾ��*/
	public boolean delWog(int id);
	
	/**�޸�*/
	public boolean modifiedWog(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Wog> queryWogs(Map<String,Object> map);
	
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.TransportPeople;

public interface TransportPeopleService {
	/**���*/
	public boolean add(TransportPeople tp);
	
	/**��ѯ*/
	public List<TransportPeople> quertByAccount(Map<String,Object> map);
	
	/**�޸�����*/
	public boolean modifiData(Map<String,Object> map);
	
	/**ɾ��*/
	public boolean del(int peopleId);
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.TransportCompany;

public interface TransportCompanyService {
	/**���*/
	public boolean add(TransportCompany transportCompany);
	
	/**��ѯ*/
	public List<TransportCompany> quertByAccount(Map<String,Object> map);
	
	/**�޸�����*/
	public boolean modifiData(Map<String,Object> map);
	
	/**ɾ��*/
	public boolean del(int companyId);
}

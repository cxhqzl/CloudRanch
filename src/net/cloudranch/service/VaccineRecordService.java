package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.VaccineRecord;

public interface VaccineRecordService {
	/**��Ӽ�¼*/
	public boolean add(VaccineRecord vaccineRecord);
	
	/**ɾ����¼*/
	public boolean del(int vaccineRecordId);
	
	/**�޸�*/
	public boolean modified(Map<String,Object> map);
	
	/**��ѯ*/
	public VaccineRecord query(Map<String,Object> map);
	
	/**��ѯ�������������м�¼*/
	public List<VaccineRecord> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**��ѯ��¼����*/
	public int queryCount(Map<String,Object> map);
}

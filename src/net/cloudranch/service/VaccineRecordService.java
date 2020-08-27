package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.VaccineRecord;

public interface VaccineRecordService {
	/**添加记录*/
	public boolean add(VaccineRecord vaccineRecord);
	
	/**删除记录*/
	public boolean del(int vaccineRecordId);
	
	/**修改*/
	public boolean modified(Map<String,Object> map);
	
	/**查询*/
	public VaccineRecord query(Map<String,Object> map);
	
	/**查询符合条件的所有记录*/
	public List<VaccineRecord> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**查询记录总数*/
	public int queryCount(Map<String,Object> map);
}

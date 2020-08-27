package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.GrowInfo;

public interface GrowInfoService {
	/**添加*/
	public boolean add(GrowInfo growInfo);
	
	/**删除*/
	public boolean del(int growInfoId);
	
	/**修改*/
	public boolean modified(Map<String,Object> map);
	
	/**查询*/
	public GrowInfo query(Map<String,Object> map);
	
	/**查询最近一次羊的照片*/
	public String queryImageBySheepId(Map<String,Object> map);
	
	/**查询符合条件的所有记录*/
	public List<GrowInfo> queryBySheepIdAndAccount(Map<String,Object> map);
	
	/**查询记录总数*/
	public int queryCount(Map<String,Object> map);
	
	/**获取生长信息最后一张图片*/
	public String getLastImage(Map<String,Object> map);
}

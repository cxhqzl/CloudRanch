package net.cloudranch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cloudranch.dao.QueryInfosForView;
import net.cloudranch.viewDomain.Crops;
import net.cloudranch.viewDomain.SiteInfos;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("viewService")
public class ViewService {
	
	@Autowired
	private QueryInfosForView queryViewDao;
	
	/**
	 * 查询站点信息-视图
	 * @param map
	 * @return
	 */
	public List<SiteInfos> selectSiteInfos(Map<String,Object> map){
		return queryViewDao.selectSiteInfos(map);
	}
	
	/**
	 * 查询作物种类-视图
	 * @param map
	 * @return
	 */
	public List<Crops> selectCrops(Map<String,Object> map){
		return queryViewDao.selectCrops(map);
	}
}

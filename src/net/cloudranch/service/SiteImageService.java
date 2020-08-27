package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.SiteImage;

public interface SiteImageService {
	/**���ͼƬ��¼*/
	public boolean addSiteImage(SiteImage siteImage);
	
	/**ɾ��ͼƬ*/
	public boolean delSiteImage(int id);
	
	/**�޸�ͼƬ*/
	public boolean modifiySiteImage(Map<String,Object> map);
	
	/**��ѯվ��ͼƬ��Ϣ*/
	public List<SiteImage> querySiteImages(Map<String,Object> map);
}

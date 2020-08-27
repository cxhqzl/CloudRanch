package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.SiteImage;

public interface SiteImageService {
	/**Ìí¼ÓÍ¼Æ¬¼ÇÂ¼*/
	public boolean addSiteImage(SiteImage siteImage);
	
	/**É¾³ıÍ¼Æ¬*/
	public boolean delSiteImage(int id);
	
	/**ĞŞ¸ÄÍ¼Æ¬*/
	public boolean modifiySiteImage(Map<String,Object> map);
	
	/**²éÑ¯Õ¾µãÍ¼Æ¬ĞÅÏ¢*/
	public List<SiteImage> querySiteImages(Map<String,Object> map);
}

package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Vido;

public interface VidoService {
	
	/**Ìí¼Ó*/
	public boolean addVido(Vido vido);
	
	/**É¾³ý*/
	public boolean delVido(int id);
	
	/**ÐÞ¸Ä*/
	public boolean modifiedVido(Map<String,Object> map);
	
	/**²éÑ¯*/
	public List<Vido> queryVidos(Map<String,Object> map);
	
}

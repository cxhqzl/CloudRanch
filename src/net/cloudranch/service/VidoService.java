package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.Vido;

public interface VidoService {
	
	/**���*/
	public boolean addVido(Vido vido);
	
	/**ɾ��*/
	public boolean delVido(int id);
	
	/**�޸�*/
	public boolean modifiedVido(Map<String,Object> map);
	
	/**��ѯ*/
	public List<Vido> queryVidos(Map<String,Object> map);
	
}

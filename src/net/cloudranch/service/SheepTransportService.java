package net.cloudranch.service;

import java.util.Map;

import net.cloudranch.domain.SheepTransport;

public interface SheepTransportService {
	/**Ìí¼Ó*/
	public boolean add(SheepTransport st);
	
	public SheepTransport getSheepTransport(Map<String,Object> map);
}

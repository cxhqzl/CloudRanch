package net.cloudranch.service;

import java.util.List;
import java.util.Map;

import net.cloudranch.domain.UnitTransport;

public interface UnitTransportService {
	/**Ìí¼Ó*/
	public boolean add(UnitTransport ut);
	
	public List<UnitTransport> getUnitTransport(Map<String,Object> map);
}

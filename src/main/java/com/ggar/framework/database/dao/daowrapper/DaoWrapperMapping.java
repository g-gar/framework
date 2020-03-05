package com.ggar.framework.database.dao.daowrapper;

import java.util.Map;
import java.util.Set;

public class DaoWrapperMapping {

	private final Map<Class<?>, String> mappings;

	public DaoWrapperMapping(Map<Class<?>, String> mappings) {
		this.mappings = mappings;
	}
	
	public String getTable(Class<?> key) {
		return this.mappings.get(key);
	}
	
	public String registerMapping(Class<?> key, String value) {
		return this.mappings.put(key, value);
	}
	
	public Set<Class<?>> getMappingKeys() {
		return this.mappings.keySet();
	}
}

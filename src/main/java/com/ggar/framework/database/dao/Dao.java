package com.ggar.framework.database.dao;

import java.util.List;

import com.ggar.framework.model.BaseEntity;

public interface Dao<T extends BaseEntity> {

	boolean add(T entity);
	T remove(T entity);
	List<T> removeAll();
	T update(T entity);
	boolean contains(T entity);
	List<T> getAll();
	T get(Object id);
	
}

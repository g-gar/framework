package com.ggar.framework.database.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ggar.framework.model.BaseEntity;
import com.ggar.framework.model.annotation.PrimaryKey;

public abstract class BasicDatabaseDao<T extends BaseEntity> implements Dao<T> {

	private final List<T> entities;

	public BasicDatabaseDao() {
		this(new ArrayList<T>());
	}

	public BasicDatabaseDao(List<T> entities) {
		this.entities = entities;
	}

	@Override
	public boolean add(T entity) {
		return this.entities.add(entity);
	}

	@Override
	public T remove(T entity) {
		return this.entities.remove(this.entities.indexOf(entity));
	}
	
	@Override
	public List<T> removeAll() {
		List<T> entities = new ArrayList() {{
			addAll(BasicDatabaseDao.this.entities);
		}};
		this.entities.removeAll(this.entities);
		return entities;
	}

	@Override
	public T update(T entity) {
		return null;
	}

	@Override
	public boolean contains(T entity) {
		return this.entities.indexOf(entity) >= 0;
	}

	@Override
	public List<T> getAll() {
		return this.entities.subList(0, this.entities.size());
	}

	@Override
	public T get(Object id) {

		try {
			for (T entity : this.entities) {
				for (Field f : entity.getClass().getFields()) {
					if (f.getAnnotationsByType(PrimaryKey.class) != null && (
						(
							(!f.getType().isPrimitive() && f.get(entity).equals(id))
							||
							(f.getType().isPrimitive() && f.get(entity) == id))
						)
					) {
						return entity;
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

}

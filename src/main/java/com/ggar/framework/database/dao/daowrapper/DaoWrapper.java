package com.ggar.framework.database.dao.daowrapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ggar.framework.database.Database;
import com.ggar.framework.database.connectionpool.ConnectionPool;
import com.ggar.framework.database.dao.Dao;
import com.ggar.framework.model.BaseEntity;

public class DaoWrapper<T extends BaseEntity> implements Dao<T> {

	private final ConnectionPool pool;
	private final DaoWrapperMapping map;
	private final Dao<T> dao;
	
	public DaoWrapper(ConnectionPool pool, DaoWrapperMapping map, Dao<T> dao) {
		this.pool = pool;
		this.map = map;
		this.dao = dao;
	}
	
	@Override
	public boolean add(T entity) {
		Connection c = pool.get();
		String tablename = map.getTable(entity.getClass());
		Map<String, Object> temp = entity.getAttributes();
		String params = temp.keySet().stream()
			.map(key -> String.format("%s = '%s'", key, temp.get(key)))
			.collect(Collectors.joining(", "));
		try {
			Database.execute(c, String.format("insert into %s values (%s)", tablename, params));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public T remove(T entity) {
		return null;
	}

	@Override
	public T update(T entity) {
		return null;
	}

	@Override
	public boolean contains(T entity) {
		return false;
	}

	@Override
	public List<T> getAll() {
		return null;
	}

	@Override
	public T get(Object id) {
		return null;
	}

	@Override
	public List<T> removeAll() {
		return null;
	}

}

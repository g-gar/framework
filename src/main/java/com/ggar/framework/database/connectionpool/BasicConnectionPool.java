package com.ggar.framework.database.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class BasicConnectionPool implements ConnectionPool {
	
	protected Stack<Connection> connections;
	protected final String url;
	
	public BasicConnectionPool(String url, int poolSize) {
		this.url = url;
		this.connections = new Stack<Connection>();
		
		connections.setSize(poolSize);
		while (connections.size() < connections.capacity()) connections.push(create());
	}

	@Override
	public Connection get() {
		return connections.pop();
	}

	@Override
	public boolean release(Connection connection) {
		return connections.push(connection).equals(connection);
	}
	
	@Override
	public void shutdown() throws SQLException {
		for (Connection con : this.connections) {
			con.close();
		}
		this.connections.clear();
	}

	private Connection create() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(this.url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}

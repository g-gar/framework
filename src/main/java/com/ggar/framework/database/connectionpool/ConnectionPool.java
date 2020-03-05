package com.ggar.framework.database.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {

	public Connection get();
	public boolean release(Connection connection);
	public void shutdown() throws SQLException;
	
}

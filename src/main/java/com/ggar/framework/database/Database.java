package com.ggar.framework.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
	public static ResultSet execute(Connection connection, String query) throws SQLException {
		Statement stmt;
		ResultSet rs = null;
		stmt = connection.createStatement();
		query = query.toLowerCase().trim();
		if (query.startsWith("select")) {
			rs = stmt.executeQuery(query);
		} else {
			stmt.executeUpdate(query);
		}
		return rs;
	}

	public static List<Map<String, Object>> parseResultSet(ResultSet rs) {
		List<Map<String, Object>> result = null;

		try {
			result = new ArrayList<Map<String, Object>>();
			while (rs != null && rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				Map<String, Object> map = new HashMap<String, Object>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}

				result.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}

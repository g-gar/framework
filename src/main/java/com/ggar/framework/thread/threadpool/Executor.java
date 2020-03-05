package com.ggar.framework.thread.threadpool;

import java.sql.SQLException;

public interface Executor<T extends Task, ReturnType> {

	public ReturnType execute(T task) throws SQLException;
}

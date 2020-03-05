package com.ggar.framework.thread.threadpool;

import java.sql.SQLException;
import java.util.logging.Logger;

public class BasicTaskExecutor<T extends Task<ReturnType>, ReturnType> implements Executor<T, ReturnType>{

	private ReturnType result;
	private final static Logger log = Logger.getLogger(BasicTaskExecutor.class.getName());

	@Override
	public ReturnType execute(T task) throws SQLException {
		log.fine(String.format("Executing Task: %s", task));
		result = task.execute();
		log.fine(String.format("Executed Task: %s", task));
		return result;
	}

}

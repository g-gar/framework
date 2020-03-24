package com.ggar.framework.thread.threadpool;

import java.util.logging.Logger;

public class BasicTaskExecutor<T, R> implements Executor<T, R>{

	private R result;
	private final static Logger log = Logger.getLogger(BasicTaskExecutor.class.getName());

	@Override
	public R execute(Task<T, R> task, T data) throws Exception {
		log.fine(String.format("Executing Task: %s", task));
		result = task.execute(data);
		log.fine(String.format("Executed Task: %s", task));
		return result;
	}
}

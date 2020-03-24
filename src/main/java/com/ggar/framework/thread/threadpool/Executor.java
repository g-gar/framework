package com.ggar.framework.thread.threadpool;

import java.sql.SQLException;

public interface Executor<T,R> {

	public R execute(Task<T,R> task, T data) throws Exception;
}

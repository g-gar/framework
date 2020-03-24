package com.ggar.framework.thread.threadpool;

import java.sql.SQLException;

public interface Task<Type, ReturnType> {
	
//	public void onstart(Runnable r);
	public ReturnType execute(Type data) throws Exception;
//	public void onstop(Runnable r);
	
}

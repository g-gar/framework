package com.ggar.framework.thread.threadpool;

import java.sql.SQLException;

public interface Task<ReturnType> {
	
//	public void onstart(Runnable r);
	public ReturnType execute() throws SQLException;
//	public void onstop(Runnable r);
	
}

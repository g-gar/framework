package com.ggar.framework.thread.threadpool;

public interface Pool<T extends Task> {

	public void start();
	public void stop();
	public Thread.State getState();
	
}

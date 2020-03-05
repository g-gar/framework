package com.ggar.framework.thread.threadpool;

public interface Queue<T extends Task> {

	public T enqueue(T item) throws InterruptedException;
	public T front() throws InterruptedException;
	public T dequeue() throws InterruptedException;
	public Boolean isEmpty() throws InterruptedException;	
	public Boolean isFull() throws InterruptedException;
	public int maxSize();
	
}

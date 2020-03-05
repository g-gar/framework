package com.ggar.framework.thread.threadpool;

import java.util.Stack;

public class BlockingQueue<T extends Task> implements Queue<T> {
	
	public static final Integer EMPTY = 0;
	private final Stack<T> tasks;
	private final Integer threadNum;
	
	public BlockingQueue(Integer threadNum) {
		this.threadNum = threadNum;
		this.tasks = new Stack<T>();
	}

	@Override
	public synchronized T enqueue(T item) throws InterruptedException {
		while (tasks.size() == this.threadNum) {
			wait();
		}
		if (tasks.size() == EMPTY) {
			notifyAll();
		}
		return tasks.push(item);
	}

	@Override
	public synchronized T dequeue() throws InterruptedException {
		while (tasks.size() == this.threadNum) {
			wait();
		}
		if (tasks.size() == EMPTY) {
			notifyAll();
		}
		return tasks.remove(0);
	}
	
	@Override
	public T front() throws InterruptedException {
		while (tasks.size() == this.threadNum) {
			wait();
		}
		if (tasks.size() == EMPTY) {
			notifyAll();
		}
		return !tasks.isEmpty() ? tasks.peek() : null;
	}

	@Override
	public synchronized Boolean isEmpty() throws InterruptedException {
		return tasks.size() == 0;
	}
	
	@Override
	public synchronized Boolean isFull() throws InterruptedException {
		return tasks.size() == threadNum;
	}

	@Override
	public int maxSize() {
		return this.threadNum;
	}
	
}

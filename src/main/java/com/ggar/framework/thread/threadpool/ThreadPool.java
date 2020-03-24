package com.ggar.framework.thread.threadpool;

import java.util.logging.Logger;

public class ThreadPool<T, R> implements Pool<Task<T,R>> {

	private final Logger log = Logger.getLogger(ThreadPool.class.getName());
	private final Queue<Task<T,R>> queue;
	private Thread mainThread;
	private volatile int workers;
	private volatile boolean exit = false;

	public ThreadPool(Queue<Task<T,R>> queue) {
		this.queue = queue;
		this.workers = 0;
	}

	public synchronized void start() {
		this.mainThread = new Thread(() -> {
			log.fine("ThreadPool started listening");
			while (!ThreadPool.this.exit) {
				if (workers >= 0 && workers < queue.maxSize()) {
					Thread t = new Thread(() -> {
						try {
							synchronized (queue) {
								if (!queue.isEmpty()) {
									Task<T,R> task = ThreadPool.this.queue.dequeue();
									log.fine(String.format("Found new Task %s", task));
									Executor executor = new BasicTaskExecutor();
									try {
										executor.execute(task, null);
									} catch (Exception e) {
										e.printStackTrace();
									}
									Thread.sleep(1);
								}
							}
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						} finally {
							--ThreadPool.this.workers;
						}
					});
					t.setName("Thread No " + ThreadPool.this.workers);
					++ThreadPool.this.workers;
					t.start();
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			log.fine("ThreadPool stoppped listening");
		});
		this.mainThread.start();
	}

	public Thread.State getState() {
		return this.mainThread.getState();
	}

	@Override
	public void stop() {
		this.exit = true;
	}
}

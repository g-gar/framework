package com.ggar.framework.thread.threadpool;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ThreadPool<T extends Task<?>> implements Pool<T> {

	private final Logger log = Logger.getLogger(ThreadPool.class.getName());
	private final Queue<T> queue;
	private Thread mainThread;
	private volatile int workers;
	private volatile boolean exit = false;

	public ThreadPool(Queue<T> queue) {
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
									T task = ThreadPool.this.queue.dequeue();
									log.fine(String.format("Found new Task %s", task));
									Executor executor = new BasicTaskExecutor();
									try {
										executor.execute(task);
									} catch (SQLException e) {
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

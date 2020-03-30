package com.ggar.framework.thread.threadpool;

import java.lang.reflect.Field;

public class ThreadPoolTest {

	public void testThreadPool() {
		Queue<Task<Integer, String>> queue = new BlockingQueue<Task<Integer,String>>(Runtime.getRuntime().availableProcessors());
		Pool<Task<Integer, String>> pool = new ThreadPool<Integer,String>(queue);
		
		pool.start();
		
		new Thread(() -> {
			int max = 200;
			int temp = 0;
			while (temp < max) {
				try {
					synchronized(pool) {
						IntegerToStringTask t = new IntegerToStringTask(temp++);
						queue.enqueue(t);
					}
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			pool.stop();
		}).start();
		
	}
	
	public static void main(String[] args) {
		new ThreadPoolTest().testThreadPool();
	}
	
}

class IntegerToStringTask implements Task<Integer, String> {

	private final Integer num;
	
	public IntegerToStringTask(Integer num) {
		this.num = num;
	}
	
	@Override
	public String execute(Integer integer) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "" + (this.num * 10);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		try {
			builder.append(this.getClass().getSimpleName() + " [");
			Field[] oa;
			for (Field f : (oa = this.getClass().getDeclaredFields())) {
				f.setAccessible(true);
				builder.append( f.getName() + ": " + f.get(this) + (!f.equals(oa[oa.length-1]) ? ", " : ""));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return builder.append("]").toString();
		}
	}
}

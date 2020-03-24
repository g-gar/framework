package com.ggar.framework.thread;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.logging.Logger;

public class EventEmitterThread<T,R> extends Thread implements Map<String, Function<T, R>> {

	protected final Logger log;
	private final Map<String, Function<T,R>> events;
	private final AtomicBoolean run;

	public EventEmitterThread() {
		this(new HashMap<String, Function<T,R>>());
	}
	
	public EventEmitterThread(Map<String, Function<T,R>> events) {
		super();
		this.run = new AtomicBoolean(false);
		this.events = events;
		log = Logger.getAnonymousLogger();
	}
	
	public Object emit(String key, T param) {
		log.info(String.format("Trying to emit event with name [%s] (%s found)", key, this.containsKey(key) ? "not" : ""));
		return this.containsKey(key) ? this.get(key).apply(param) : null;
	}
	
	@Override
	public void interrupt() {
		super.interrupt();
		if (!isInterrupted()) {
			this.run.set(false);
		}
	}

	@Override
	public boolean isInterrupted() {
		return super.isInterrupted() && !run.get();
	}

	@Override
	public int size() {
		return events.size();
	}

	@Override
	public boolean isEmpty() {
		return events.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return events.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return events.containsValue(value);
	}

	@Override
	public Function<T,R> get(Object key) {
		return events.get(key);
	}

	@Override
	public Function<T,R> put(String key, Function<T,R> value) {
		return events.put(key, value);
	}

	@Override
	public Function<T,R> remove(Object key) {
		return events.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Function<T,R>> m) {
		events.putAll(m);
	}

	@Override
	public void clear() {
		events.clear();
	}

	@Override
	public Set<String> keySet() {
		return events.keySet();
	}

	@Override
	public Collection<Function<T,R>> values() {
		return events.values();
	}

	@Override
	public Set<Entry<String, Function<T,R>>> entrySet() {
		return events.entrySet();
	}
}

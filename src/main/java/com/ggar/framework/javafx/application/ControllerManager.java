package com.ggar.framework.javafx.application;

import java.util.HashMap;

import com.ggar.framework.javafx.application.Exception.StageNotFoundException;
import com.ggar.framework.javafx.application.controller.Controller;

public class ControllerManager<S extends IStageIdentifier> extends HashMap<S, Controller> {
	
	private static final long serialVersionUID = 1L;
	private static Controller current;
	
	public ControllerManager() {
		super();
	}
	
	public Controller getCurrent() {
		return current;
	}

	@Override
	public Controller get(Object key) {
		Controller controller = super.get(key);
		current = controller;
		return controller;
	}
	
	@Override
	public Controller put(S key, Controller controller) {
		return super.put(key, controller);
	}
}

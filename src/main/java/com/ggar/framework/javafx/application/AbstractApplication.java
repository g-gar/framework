package com.ggar.framework.javafx.application;

import java.util.logging.Logger;

import com.ggar.framework.container.Container;
import com.ggar.framework.container.DefaultContainer;
import com.ggar.framework.javafx.application.controller.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public abstract class AbstractApplication extends Application implements Container {

	protected final Logger log;
	protected final Container container;
	
	public AbstractApplication() {
		this(new DefaultContainer() {});
	}
	
	public AbstractApplication(Container container) {
		this.container = container;
		log = Logger.getAnonymousLogger();
	}
	
	public void execute(Runnable runnable) {
		if (Platform.isFxApplicationThread()) {
			runnable.run();
		} else {
			Platform.runLater(runnable);
		}
	}

	public void show(IStageIdentifier si) {
		try {
			Controller current;
			ControllerManager<IStageIdentifier> cm = container.get(ControllerManager.class);
			if ((current = cm.getCurrent()) != null) {
				current.getStage().hide();
			}

			Stage stage = cm.get(si).getStage();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> T register(T entity) {
		return container.register(entity);
	}

	@Override
	public <T> T register(Class<T> clazz, T entity) {
		return container.register(clazz, entity);
	}

	@Override
	public <T> T unregister(Class<T> clazz) {
		return container.unregister(clazz);
	}

	@Override
	public <T> T unregister(Class<T> clazz, T entity) {
		return container.unregister(clazz, entity);
	}

	@Override
	public Boolean containsClass(Class clazz) {
		return container.containsClass(clazz);
	}

	@Override
	public <T> T get(Class<T> clazz) {
		return container.get(clazz);
	}
	
}

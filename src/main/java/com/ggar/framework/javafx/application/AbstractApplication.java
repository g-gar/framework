package com.ggar.framework.javafx.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public abstract class AbstractApplication extends Application {

	protected final ControllerManager<? extends IStageIdentifier> stageManager;
	
	public AbstractApplication() {
		this(new ControllerManager<IStageIdentifier>());
	}
	
	public AbstractApplication(ControllerManager<? extends IStageIdentifier> stageManager) {
		super();
		this.stageManager = stageManager;
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
//			ControllerManager<IStageIdentifier> sm = stageManager.getInstance();
//			sm.getCurrent().hide();
//			Stage stage = sm.get(si);
//			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
//				stageManager.getInstance().get(si).show()
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	public StageManager<? extends IStageIdentifier> getStageManager() {
//		return this.stageManager;
//	}
	
}

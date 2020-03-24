package com.ggar.framework.javafx.application.controller;

import javafx.stage.Stage;

public interface Controller {

	public Stage getStage();
	
	public void start();
	public void destroy();
	
}

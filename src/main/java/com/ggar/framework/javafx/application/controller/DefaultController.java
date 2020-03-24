package com.ggar.framework.javafx.application.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class DefaultController extends VBox implements Controller {
	public URL fxml;
	public FXMLLoader loader;
	private final Stage stage;
	
	public DefaultController(URL fxml) {
		this.loader = new FXMLLoader(fxml);
		this.loader.setRoot(this);
		this.loader.setController(this);

		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
		}
	}

	@Override
	public Stage getStage() {
		return stage;
	}
	
	@Override
	public void destroy() {
		//Do nothing
	}
}

package com.ggar.framework.javafx.application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.ggar.framework.javafx.application.ControllerManager.Controller;
import com.ggar.framework.javafx.application.Exception.StageNotFoundException;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ControllerManager<S extends IStageIdentifier> extends HashMap<S, Controller> {
	
	private static final long serialVersionUID = 1L;
	private static ControllerManager instance;
	private static Controller current;
	
	public static ControllerManager getInstance() {
		if (instance == null)
			instance = new ControllerManager();
		return instance;
	}
	
	public static Controller getCurrent() {
		return current;
	}

	@Override
	public Controller get(Object key) {
		Controller temp = null;
		try {
			
			if ((temp = super.get(key)) == null || temp.equals(null)) {
				throw new StageNotFoundException();
			} else {
				temp = super.get(key);
				current = temp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	public static interface Controller {
		
	}
	
	public static abstract class DefaultController implements Controller {
		public URL fxml;
		public FXMLLoader loader;
		public Controller instance;
		
		public DefaultController(URL fxml) {
			this(fxml, false);
		}
		
		public DefaultController(URL fxml, boolean returnInstance) {
			this.loader = new FXMLLoader(fxml);
			this.loader.setRoot(this);
			this.loader.setController(this);

			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = returnInstance ? this : null;
		}
	}
	
	
}

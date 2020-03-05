package com.ggar.framework.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;

public class SingleJsonObject {
	
	public static Properties fromJson(String filename) {
		Gson gson = new Gson();
		Properties properties = null;
		
		try (Reader reader = new FileReader(filename)) {
			properties = gson.fromJson(reader, Properties.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public static void toJson(String filename, Map<String, Object> properties) {
		Gson gson = new Gson();
		
		try (Writer writer = new FileWriter(filename)) {
			gson.toJson(properties, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

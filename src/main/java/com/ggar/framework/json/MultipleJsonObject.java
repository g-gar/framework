package com.ggar.framework.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class MultipleJsonObject {
	
	private static final GsonBuilder builder = new GsonBuilder();
	
	public static GsonBuilder getBuilder() {
		return builder;
	}

	public static <T> List<T> fromJson(String filename) {
		List<T> users = new ArrayList<T>();
		Gson gson;
		Type type;
		
		try  (Reader reader = new FileReader(filename)) {
			gson = builder.create();
			type = new TypeToken<List<T>>() {}.getType();
			
			users = gson.fromJson(reader, type);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static <T> void toJson(String filename, List<T> entities) {
		Gson gson;
		
		try (Writer writer = new FileWriter(filename)) {			
			gson = builder.setPrettyPrinting().create();
			gson.toJson(entities);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

class Deserializer<T> implements JsonDeserializer<T> {

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		
		return null;
	}
	
}

class Serializer<T> implements JsonSerializer<T> {

	@Override
	public JsonElement serialize(T entity, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		
		return null;
	}
	
}
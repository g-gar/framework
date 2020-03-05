package com.ggar.framework.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ReflectionUtils {

	public static <T> Class<?> getGenericType(T entity) {
		Class<?> res = null;

		try {
			ParameterizedType a = (ParameterizedType) entity.getClass().getGenericSuperclass();
			Type type = a.getActualTypeArguments()[0];

			String pattern = "([\\w\\.]*<)([\\w\\.]*)([\\w\\.]*>)+";
			String updated = type.getTypeName().replaceAll(pattern, "$2");

			res = Class.forName(updated);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static <T> List<Class<?>> getGenericTypes(T entity) {
		List<Class<?>> res = null;

		try {
			ParameterizedType a = (ParameterizedType) entity.getClass().getGenericSuperclass();
			for (Type type : a.getActualTypeArguments()) {
				String pattern = "([\\w\\.]*<)([\\w\\.]*)([\\w\\.]*>)+";
				String updated = type.getTypeName().replaceAll(pattern, "$2");
				res.add(Class.forName(updated));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static List<Class<?>> getAllInitiatedClasses() {
		ClassLoader cl = null;
		List<Class<?>> classes = new ArrayList<Class<?>>();
		try {
			cl = Thread.currentThread().getContextClassLoader();
			Field f = ClassLoader.class.getDeclaredField("classes");
			f.setAccessible(true);
			for (Class<?> c : (Vector<Class<?>>) f.get(cl)) {
				classes.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return classes;
	}

	
	/**
	 * TODO: Las clases están obligadas a implementar las interfaces para que esto funcione.
	 * @param <T>
	 * @param obj
	 * @param type
	 * @return
	 */
	public static <T> T usesParameterizedType(T obj, Class type) {
		Object result = null;
		Class<T> clazz = (Class<T>) obj.getClass();
		Type[] genericInterfaces = clazz.getGenericInterfaces();
		exitloop:
		for (Type genericInterface : genericInterfaces) {
			if (genericInterface instanceof ParameterizedType) {
				Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
				for (Type genericType : genericTypes) {
					if (genericType.equals(type)) {
						result = obj;
						break exitloop;
					}
				}
			}
		}
		
		return (T) result;
	}

}

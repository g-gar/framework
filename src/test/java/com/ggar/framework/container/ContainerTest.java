package com.ggar.framework.container;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.ggar.framework.database.dao.Dao;
import com.ggar.framework.model.BaseEntity;
import com.ggar.framework.model.annotation.ReferencesTable;
import com.ggar.framework.reflection.ReflectionUtils;

public class ContainerTest {

	public void filterBySuperClassTest() {
		Container c = new DefaultContainer() {};
		c.register(new User1Dao());
		c.register(new User2Dao());
		
		Object result = null;
		List<Class> classes = ((DefaultContainer) c).filterByInterface(Dao.class);
		for (Object object : classes) {
			result = ReflectionUtils.usesParameterizedType(object, User1.class);
			if (result != null) break;
		}
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		new ContainerTest().filterBySuperClassTest();
	}

}

@ReferencesTable("user1")
class User1 extends BaseEntity {
}

@ReferencesTable("user2")
class User2 extends BaseEntity {
}

class User1Dao implements Dao<User1> {

	@Override
	public boolean add(User1 entity) {
		return false;
	}

	@Override
	public User1 remove(User1 entity) {
		return null;
	}

	@Override
	public User1 update(User1 entity) {
		return null;
	}

	@Override
	public boolean contains(User1 entity) {
		return false;
	}

	@Override
	public List<User1> getAll() {
		return null;
	}

	@Override
	public User1 get(Object id) {
		return null;
	}

	@Override
	public List<User1> removeAll() {
		return null;
	}
}

class User2Dao implements Dao<User2> {

	@Override
	public boolean add(User2 entity) {
		return false;
	}

	@Override
	public User2 remove(User2 entity) {
		return null;
	}

	@Override
	public User2 update(User2 entity) {
		return null;
	}

	@Override
	public boolean contains(User2 entity) {
		return false;
	}

	@Override
	public List<User2> getAll() {
		return null;
	}

	@Override
	public User2 get(Object id) {
		return null;
	}

	@Override
	public List<User2> removeAll() {
		return null;
	}

}

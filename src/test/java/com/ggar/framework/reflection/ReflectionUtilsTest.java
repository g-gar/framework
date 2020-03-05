package com.ggar.framework.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import com.ggar.framework.container.Container;
import com.ggar.framework.container.DefaultContainer;
import com.ggar.framework.database.connectionpool.BasicConnectionPool;
import com.ggar.framework.database.connectionpool.ConnectionPool;
import com.ggar.framework.database.dao.BasicDatabaseDao;
import com.ggar.framework.database.dao.Dao;
import com.ggar.framework.model.BaseEntity;
import com.ggar.framework.model.annotation.ReferencesTable;
import com.ggar.framework.test.TestRunner;

@RunWith(TestRunner.class)
public class ReflectionUtilsTest {

	@Test
	public void getGenericTypeByReflection() {
		
		List<TestEntity> l = new ArrayList<TestEntity>() {};

		assertEquals(TestEntity.class, ReflectionUtils.getGenericType(l));
		
	}
	
	public void getAllSystemClasses() {
		List<Class<?>> classes = ReflectionUtils.getAllInitiatedClasses();
		
		for (Class c : classes) {
			System.out.println(c);
		}
	}
	
	public static void main(String[] args) {
		new ReflectionUtilsTest().getAllSystemClasses();
	}
}

class TestEntity {
	private final int id;
	public TestEntity(int id) {this.id = id;}
	public int getId() {return this.id;}
}
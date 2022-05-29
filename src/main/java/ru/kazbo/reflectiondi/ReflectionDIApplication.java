package ru.kazbo.reflectiondi;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class ReflectionDIApplication {
	
	private static final Map<Class<?>,Object> dependencyVariablesContainer = new HashMap<>();
	
	public static void start(String[] args) throws Exception {
		Object obj = ReflectionUtil.createImplementedObject("ru.kazbo.reflectiondi.module.TestModule");
		createInstanceForAnnotatedFields(obj);
		// run two times
		for(int i = 0; i < 2; i++) {
			runMethodFromImplementedObject(obj);
		}
	}
	
	private static void createInstanceForAnnotatedFields(Object obj) throws Exception {
		Class<?> clazz = obj.getClass();
		Field[] fields = ReflectionUtil.getAnnotatedFields(clazz);
		for(Field field : fields) {
			Class<?> type = field.getType();
			var object = ReflectionUtil.createObjectByClass(type);
			dependencyVariablesContainer.put(type, object);
		}
	}
	
	private static void runMethodFromImplementedObject(Object obj) throws Exception {
		ReflectionUtil.putObjectsIntoObject(obj, dependencyVariablesContainer);
		obj.getClass().getMethod("run").invoke(obj, null);
	}
}
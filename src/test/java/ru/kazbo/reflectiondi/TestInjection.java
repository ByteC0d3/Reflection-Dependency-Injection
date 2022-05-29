package ru.kazbo.reflectiondi;

import ru.kazbo.reflectiondi.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.util.stream.Stream;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class TestInjection {
	static Class<?> clazz;
	
	@BeforeEach
	void init() throws ClassNotFoundException {
		clazz = Class.forName("ru.kazbo.reflectiondi.module.TestModule");
	}
	
	@Disabled
	@Test
	void testIsImplementedRunnable() throws Exception {
		boolean isImplemented = ReflectionUtil.isImplementedRunnable(clazz);
		Assertions.assertEquals(true, isImplemented);
	}
	
	@Disabled
	@Test
	void testAnnotatedFields() throws Exception {
		Field[] annotatedFields = ReflectionUtil.getAnnotatedFields(clazz);
		Stream.of(annotatedFields).forEach(field -> System.out.println(field.getName()));
	}

	@Test
	void startApplication() throws Exception {
		ReflectionDIApplication.start(new String[]{});
	}
}
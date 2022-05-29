package ru.kazbo.reflectiondi;

import java.util.Map;
import java.util.stream.Stream;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import ru.kazbo.reflectiondi.annotation.Injected;
import ru.kazbo.reflectiondi.exception.DependecyAnnotatedException;

class ReflectionUtil {
	
	private static final Class<Injected> injectedAnnotation = Injected.class;
	
	public static boolean isImplementedRunnable(Class<?> clazz) {
		Class<?>[] implementations = clazz.getInterfaces();
		for(Class<?> implementation : implementations) {
			if(implementation.equals(Runnable.class))
				return true;
		}
		return false;
	}
	
	public static void putObjectsIntoObject(Object obj, Map<Class<?>,Object> map) throws Exception {
		Class<?> objectType = obj.getClass();
		Field[] annotatedFields = getAnnotatedFields(objectType);
		for(Field annotatedField : annotatedFields) {
			Class<?> key = annotatedField.getType();
			Object dependencyObject = map.get(key);
			annotatedField.set(obj, dependencyObject);
		}
	}
	
	public static boolean isClassAnnotatedForInjection(Class<?> clazz) {
		return clazz.getAnnotation(injectedAnnotation) != null;
	}
	
	/**
	 * Get fields of class annotated by ru.kazbo.reflectiondi.annotation.Injected
	 */
	public static Field[] getAnnotatedFields(Class<?> clazz) {
			Field[] allFields = clazz.getDeclaredFields();
			return Stream.of(allFields)
				.filter(field -> field.isAnnotationPresent(injectedAnnotation)
								 && isClassAnnotatedForInjection(field.getType()))
				.map(field -> {
					field.setAccessible(true);
					return field;
				})
				.toArray(size -> new Field[size]);
	}
	
	public static Object createImplementedObject(String className) throws ClassNotFoundException,
	NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		if(!isImplementedRunnable(clazz)) {
			String message = "Module [%s] is not annotated by []"
				.formatted(injectedAnnotation.getName(), clazz.getName());
			throw new DependecyAnnotatedException(message);
		}
		Object thisObject = createObjectByClass(clazz);
		return thisObject;
	}
	
	public static Object createObjectByName(String name) throws ClassNotFoundException,
	NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<?> clazz = Class.forName(name);
		return createObjectByClass(clazz);
	}
	public static Object createObjectByClass(Class<?> clazz) throws ClassNotFoundException,
	NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<?> defaultConstructor = clazz.getConstructor();
		Object thisObject = defaultConstructor.newInstance();
		return thisObject;
	}
}
package ru.kazbo.reflectiondi.pojo;

import ru.kazbo.reflectiondi.annotation.Injected;

@Injected
public class Person {
	
	private String name;
	private int age;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "[name=\"%s\",age=%d]".formatted(name, age);
	}
}
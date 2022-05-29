package ru.kazbo.reflectiondi.pojo;

import ru.kazbo.reflectiondi.annotation.Injected;

@Injected
public class User {
	
	private String name, password;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "[name=\"%s\",passoword=\"%s\"]".formatted(name, password);
	}
}
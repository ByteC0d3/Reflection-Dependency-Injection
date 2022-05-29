package ru.kazbo.reflectiondi.module;

import ru.kazbo.reflectiondi.annotation.Injected;
import ru.kazbo.reflectiondi.pojo.Person;
import ru.kazbo.reflectiondi.pojo.User;

public class TestModule implements Runnable {
	
	@Injected
	private Person person;
	
	@Injected
	private User user;
	
	//@Override
	public void run() {
		// first time injecting - set name
		if(person.getName() == null && user.getName() == null) {
			person.setName("Alex");
			user.setName("Username");
		// second time - set age
		} else {
			person.setAge(21);
			user.setPassword("helloworld");
		}
		// every step of injecting - println
		System.out.println(person);
		System.out.println(user);
	}
}
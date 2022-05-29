package ru.kazbo.reflectiondi.exception;

public class DependecyAnnotatedException extends RuntimeException {
	public DependecyAnnotatedException(String message) {
		super(message);
	}
}
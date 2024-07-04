package com.graphql.exception;

public class ExceptionHandler {

	public static RuntimeException throwResourceNotFoundException() {
		return new RuntimeException("Resource not found!!");
	}
}

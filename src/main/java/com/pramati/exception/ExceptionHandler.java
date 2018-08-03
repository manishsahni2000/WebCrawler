package com.pramati.exception;

public class ExceptionHandler {

	public static void handleException(String message, Exception e) {
		System.out.println(message);
		//handleThrowable(e);
	}

	public static void handleThrowable(Throwable t) {
		t.printStackTrace(System.out);
		System.out.println(t.getMessage());
	}
}

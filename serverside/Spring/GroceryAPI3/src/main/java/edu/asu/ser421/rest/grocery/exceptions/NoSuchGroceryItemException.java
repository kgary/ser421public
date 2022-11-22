package edu.asu.ser421.rest.grocery.exceptions;

@SuppressWarnings("serial")
public class NoSuchGroceryItemException extends Exception {

	public NoSuchGroceryItemException() {
		super();
	}

	public NoSuchGroceryItemException(String message) {
		super(message);
	}

	public NoSuchGroceryItemException(Throwable cause) {
		super(cause);
	}

	public NoSuchGroceryItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchGroceryItemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package edu.asu.ser421.rest.grocery.exceptions;

@SuppressWarnings("serial")
public class GroceryConflictException extends Exception {
	public GroceryConflictException() {
		super();
	}

	public GroceryConflictException(String message) {
		super(message);
	}

	public GroceryConflictException(Throwable cause) {
		super(cause);
	}

	public GroceryConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroceryConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

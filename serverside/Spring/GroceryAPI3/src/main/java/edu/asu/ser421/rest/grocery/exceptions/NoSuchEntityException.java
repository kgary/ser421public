package edu.asu.ser421.rest.grocery.exceptions;

@SuppressWarnings("serial")
public class NoSuchEntityException extends Exception {

	public NoSuchEntityException() {
		this("{ \"msg\": No such entity found}");
	}

	public NoSuchEntityException(String message) {
		super(message);
	}

	public NoSuchEntityException(Throwable cause) {
		super(cause);
	}

	public NoSuchEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

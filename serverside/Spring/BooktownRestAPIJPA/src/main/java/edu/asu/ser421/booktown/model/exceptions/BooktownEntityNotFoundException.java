package edu.asu.ser421.booktown.model.exceptions;

@SuppressWarnings("serial")
public class BooktownEntityNotFoundException extends RuntimeException {

	public BooktownEntityNotFoundException() {
		this("Author Not Found");
	}

	public BooktownEntityNotFoundException(String message) {
		super(message);
	}

	public BooktownEntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public BooktownEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BooktownEntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

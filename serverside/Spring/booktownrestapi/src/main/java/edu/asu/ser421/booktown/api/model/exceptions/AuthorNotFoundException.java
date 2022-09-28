package edu.asu.ser421.booktown.api.model.exceptions;

@SuppressWarnings("serial")
public class AuthorNotFoundException extends RuntimeException {

	public AuthorNotFoundException() {
		this("Author Not Found");
	}

	public AuthorNotFoundException(String message) {
		super(message);
	}

	public AuthorNotFoundException(Throwable cause) {
		super(cause);
	}

	public AuthorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

package edu.asu.ser421.booktown.api.model.exceptions;

@SuppressWarnings("serial")
public class AuthorInternalException extends RuntimeException {

	public AuthorInternalException() {
		this("There was an internal server error in the Author Service");
	}

	public AuthorInternalException(String message) {
		super(message);
	}

	public AuthorInternalException(Throwable cause) {
		super(cause);
	}

	public AuthorInternalException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorInternalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

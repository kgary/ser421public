package edu.asu.ser421.booktown.model.exceptions;

@SuppressWarnings("serial")
public class BooktownInternalException extends RuntimeException {

	public BooktownInternalException() {
		this("There was an internal server error in the Author Service");
	}

	public BooktownInternalException(String message) {
		super(message);
	}

	public BooktownInternalException(Throwable cause) {
		super(cause);
	}

	public BooktownInternalException(String message, Throwable cause) {
		super(message, cause);
	}

	public BooktownInternalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

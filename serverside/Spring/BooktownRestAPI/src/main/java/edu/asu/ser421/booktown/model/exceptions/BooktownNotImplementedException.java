package edu.asu.ser421.booktown.model.exceptions;

@SuppressWarnings("serial")
public class BooktownNotImplementedException extends RuntimeException {

	public BooktownNotImplementedException() {
		this("Service not yet implemented");
	}

	public BooktownNotImplementedException(String message) {
		super(message);
	}

	public BooktownNotImplementedException(Throwable cause) {
		super(cause);
	}

	public BooktownNotImplementedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BooktownNotImplementedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

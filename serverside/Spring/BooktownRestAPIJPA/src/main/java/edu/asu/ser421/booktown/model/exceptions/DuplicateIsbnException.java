package edu.asu.ser421.booktown.model.exceptions;

@SuppressWarnings("serial")
public class DuplicateIsbnException extends RuntimeException {
	public DuplicateIsbnException(String message) {
        super(message);
    }
}

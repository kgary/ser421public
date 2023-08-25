package edu.asu.ser421.rest.grocery.exceptions;

@SuppressWarnings("serial")
public class NoSuchEntityException extends Exception {

	public NoSuchEntityException() {
		this("{ \"msg\": No such entity found }");
	}

	public NoSuchEntityException(String message) {
		super(message);
	}
}

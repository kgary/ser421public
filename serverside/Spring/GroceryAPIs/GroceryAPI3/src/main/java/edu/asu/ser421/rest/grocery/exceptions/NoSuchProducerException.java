package edu.asu.ser421.rest.grocery.exceptions;

@SuppressWarnings("serial")
public class NoSuchProducerException extends NoSuchEntityException {
	public NoSuchProducerException(String pid) {
		super("{ \"msg\": \"No Such Producer found with abbreviation " + pid + " }");
	}
}

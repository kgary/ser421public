package edu.asu.ser421.rest.grocery.exceptions;

@SuppressWarnings("serial")
public class NoSuchGroceryProducerException extends NoSuchEntityException {
	public NoSuchGroceryProducerException(String gid, String pid) {
		super("{ \"msg\": \"No Such Grocery-Producer relationship between gorcery item " + gid + " and producer " + pid);
	}
}

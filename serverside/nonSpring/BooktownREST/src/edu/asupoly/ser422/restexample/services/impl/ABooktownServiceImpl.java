package edu.asupoly.ser422.restexample.services.impl;

import java.util.Random;

import edu.asupoly.ser422.restexample.services.BooktownService;

public abstract class ABooktownServiceImpl implements BooktownService {
	private static Random __r = new Random();
	/*
	 * A simple method to generate a random key. Now this can cause clashes, but I am leaving it
	 * in so you have a random error generator (and should think about how to handle that).
	 */
	protected int generateKey(int lb, int ub) {
		int rval = __r.nextInt(Math.abs(ub-lb)) + ((ub>lb) ? lb : ub);
		return rval;
	}
	
}

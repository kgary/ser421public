package edu.asupoly.ser421.simplemvcex;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ActionHandler {
	public String handleIt(HttpServletRequest req, HttpServletResponse response);
}

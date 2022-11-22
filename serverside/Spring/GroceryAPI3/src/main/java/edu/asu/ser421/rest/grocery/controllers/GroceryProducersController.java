package edu.asu.ser421.rest.grocery.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.asu.ser421.rest.grocery.services.GroceryServices;
import edu.asu.ser421.rest.grocery.model.Producer;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchGroceryItemException;

@RestController
@RequestMapping("/api/groceries/{id}/producers")
public class GroceryProducersController {
	private GroceryServices __groceryService = null;
	
	public GroceryProducersController() {
		// get the service implementation we will use
		__groceryService = GroceryServices.getGroceryService();  // we should check if it is null
	}

	@GetMapping
	public List<Producer> findAllGroceryProducers(@PathVariable ("id") String id) throws Exception {
			return __groceryService.findAllGroceryProducers(id);
	}
	
	// Exception handling
	// I always include this one - there are almost always some HTTP methods our resource does not respond to
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc) {
		return new ResponseEntity<String>("Invalid method provided to GroceryItemController", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(value = edu.asu.ser421.rest.grocery.exceptions.NoSuchGroceryItemException.class) 
	public ResponseEntity<String> handleNoSuchGroceryItemException(NoSuchGroceryItemException exc)  {
		return new ResponseEntity<String>("No Such Grocery Item", HttpStatus.NOT_FOUND);
	}
	
	// This is the catch-all, returns our 500-level error
	@ExceptionHandler(value = java.lang.Throwable.class) 
	public ResponseEntity<?> handleThrowable(java.lang.Throwable t) {
		return new ResponseEntity<String>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

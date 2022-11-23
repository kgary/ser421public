package edu.asu.ser421.rest.grocery.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.asu.ser421.rest.grocery.services.GroceryServices;
import edu.asu.ser421.rest.grocery.model.Producer;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchEntityException;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchGroceryItemException;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchProducerException;

@RestController
@RequestMapping("/api/groceries/{id}/producers")
public class GroceryProducersController {
	private GroceryServices __groceryService = null;
	
	public GroceryProducersController() {
		// get the service implementation we will use
		__groceryService = GroceryServices.getGroceryService();  // we should check if it is null
	}

	@GetMapping
	public List<Producer> findAllGroceryProducers(@PathVariable ("id") String id) throws NoSuchGroceryItemException, Exception {
			return __groceryService.findAllGroceryProducers(id);
	}
	
	@GetMapping("/{pid}")
	public Producer findOneGroceryProducer(@PathVariable ("id") String id, @PathVariable ("pid") String pid)
		throws NoSuchEntityException, Exception {
		return __groceryService.findOneGroceryProducer(id, pid);
	}
	// PUT is idempotent and will handle our create and update case, so no POST
	// Question: Do we require the entire Producer in the payload, or just the id since we should already have it?
	// I am going with just the identifier (abbreviation of a Producer), and making it another Path Variable
	// (we could have done query string or a custom payload too - there is no better RESTful answer here).
	@PutMapping("/{pid}")
	public ResponseEntity<String> addGroceryProducer(@PathVariable ("id") String id, @PathVariable ("pid") String pid) 
			throws NoSuchEntityException, Exception {
		boolean rval = __groceryService.addGroceryProducer(id, pid);
		// rval will tell us if it was an update (true) or a create (false) but since it is idempotent from a response perspective
		// it doesn't matter like it does for a resource; the relationship has no direct resource identifier so we have no Location
		// header to set. All we really need to do is tell the client all is OK (though we could customize the message if we wanted.
		// This is a semi-wrapped response; it provides a human-readable message but no deserialized object type, the client program
		// knows all it needs to know by virtue of the 204.
		return new ResponseEntity<String>("{ \"msg\": Grocery to Producer relationship created", HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{pid}")
	public ResponseEntity<String> removeGroceryProducer(@PathVariable ("id") String id, @PathVariable ("pid") String pid) 
			throws NoSuchGroceryItemException, NoSuchProducerException, Exception {

		if (__groceryService.deleteGroceryProducer(id, pid)) {
			return new ResponseEntity<String>("{ \"msg\": \"Grocery to Producer relationship deleted\"", HttpStatus.NO_CONTENT);
		} else {
			// a little different wrinkle - if we dn't have such a relationship we should also return a 404
			return new ResponseEntity<String>("{ \"msg\": \"Grocery to Producer relationship does not exist\"", HttpStatus.NOT_FOUND);   
		}
	}
	
	// Exception handling
	// I always include this one - there are almost always some HTTP methods our resource does not respond to
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc) {
		return new ResponseEntity<String>("Invalid method provided to GroceryItemController", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(value = edu.asu.ser421.rest.grocery.exceptions.NoSuchEntityException.class) 
	public ResponseEntity<String> handleNoSuchEntityException(NoSuchEntityException exc)  {
		return new ResponseEntity<String>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	// This is the catch-all, returns our 500-level error
	@ExceptionHandler(value = java.lang.Throwable.class) 
	public ResponseEntity<String> handleThrowable(java.lang.Throwable t) {
		return new ResponseEntity<String>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

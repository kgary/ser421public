package edu.asu.ser421.booktown.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.asu.ser421.booktown.api.modelhelpers.AuthorRequest;
import edu.asu.ser421.booktown.api.modelhelpers.AuthorResponse;
import edu.asu.ser421.booktown.model.Author;
import edu.asu.ser421.booktown.model.exceptions.BooktownInternalException;
import edu.asu.ser421.booktown.model.exceptions.BooktownNotImplementedException;
import edu.asu.ser421.booktown.model.exceptions.BooktownEntityNotFoundException;
import edu.asu.ser421.booktown.services.BooktownService;

@RequestMapping("/authors")
@RestController
public class AuthorController {
	private BooktownService __authorService = BooktownService.getInstance();
	
	//first endpoint, return a collection of authors
	@GetMapping
	public ResponseEntity<List<AuthorResponse>> returnAuthors() throws Throwable {
		return new ResponseEntity<List<AuthorResponse>>(AuthorResponse.convertAuthorsToResponses(__authorService.getAuthors()), HttpStatus.OK);
	}
	
	//second endpoint, return a specific Author by id
	@GetMapping("/{id}")
	public ResponseEntity<AuthorResponse> returnAuthor(@PathVariable Integer id) {
		return new ResponseEntity<AuthorResponse>(new AuthorResponse(__authorService.getAuthor(id)), HttpStatus.OK);
	}
	
	// third endpoint, create an Author via POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
		return new ResponseEntity<AuthorResponse>(new AuthorResponse(__authorService.createAuthor(authorRequest.getLastName(), authorRequest.getFirstName(), authorRequest.getBooks())), HttpStatus.CREATED);
	}
	
	// Put is interesting in that we either create a new Author like POST, or update an existing one
	// fourth endpoint, Insert or Update Author
	@PutMapping
	public ResponseEntity<AuthorResponse> insertOrUpdateAuthor(@RequestBody AuthorRequest authorRequest) {
		return createAuthor(authorRequest);

		//__authorService.createAuthor(authorRequest.getLastName(), authorRequest.getFirstName(), authorRequest.getBooks());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AuthorResponse> insertOrUpdateAuthor(@PathVariable Integer id, @RequestBody AuthorRequest authorRequest) {
		// see if there is an Author with this id
		return new ResponseEntity<AuthorResponse>(new AuthorResponse(__authorService.updateAuthor(new Author(id, authorRequest))), HttpStatus.OK);
	}
	
	// fifth endpoint, PATCH
	@PatchMapping("/{id}")
	public ResponseEntity<AuthorResponse> modifyAuthor(@PathVariable Integer id, @RequestBody AuthorRequest partialAuthor) {
		// in Patch, we only expect one or more attributes to be updated, not the whole object.
		return new ResponseEntity<AuthorResponse>(new AuthorResponse(__authorService.modifyAuthor(new Author(id, partialAuthor))), HttpStatus.OK);
	}
	
	// Sixth endpoint: DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<AuthorResponse> deleteAuthor(@PathVariable Integer id) {
		return new ResponseEntity<AuthorResponse>(new AuthorResponse(__authorService.deleteAuthor(id)), HttpStatus.NO_CONTENT);
	}
	
	// Class-level Exception Handling methods
	// Always handle 405s. Spring default is ugly and insecure JSON. Really 405 should be handled globally
	// Here we add a 405 handler as we are not supporting all HTTP methods
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc) {
		return new ResponseEntity<String>("Invalid method provided to Author Controller", HttpStatus.METHOD_NOT_ALLOWED);
	}
		
	@ExceptionHandler(value = org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleTypeMismatchException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException exc) {
		return new ResponseEntity<String>("Invalid type for Author Id, must be an Integer", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = BooktownEntityNotFoundException.class)
	public ResponseEntity<?> handleAuthorNotFoundException(BooktownEntityNotFoundException exc) {
		return new ResponseEntity<String>("In Author Controller: " + exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BooktownInternalException.class) 
	public ResponseEntity<?> handleThrowable(BooktownInternalException exc) {
		return new ResponseEntity<String>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = BooktownNotImplementedException.class) 
	public ResponseEntity<?> handleThrowable(BooktownNotImplementedException exc) {
		return new ResponseEntity<String>(exc.getMessage(), HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ExceptionHandler(value = java.lang.Throwable.class) 
	public ResponseEntity<?> handleThrowable(java.lang.Throwable t) {
		return new ResponseEntity<String>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

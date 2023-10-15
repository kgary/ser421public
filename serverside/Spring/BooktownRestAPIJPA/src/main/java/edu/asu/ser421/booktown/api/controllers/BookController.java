package edu.asu.ser421.booktown.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.asu.ser421.booktown.api.modelhelpers.BookResponse;
import edu.asu.ser421.booktown.model.exceptions.BooktownInternalException;
import edu.asu.ser421.booktown.model.exceptions.BooktownEntityNotFoundException;
import edu.asu.ser421.booktown.services.BooktownService;

@RequestMapping("/books")
@RestController
public class BookController {
	private BooktownService __booktownService = BooktownService.getInstance();
	
	//first endpoint, return a collection of Books
	@GetMapping
	public ResponseEntity<List<BookResponse>> returnBooks() throws Throwable {
		return new ResponseEntity<List<BookResponse>>(BookResponse.convertBooksToResponses(__booktownService.getBooks()), HttpStatus.OK);
	}
	
	//second endpoint, return a specific Book by id
	@GetMapping("/{isbn}")
	public ResponseEntity<BookResponse> returnBook(@PathVariable String isbn) {
		return new ResponseEntity<BookResponse>(new BookResponse(__booktownService.getBook(isbn)), HttpStatus.OK);
	}
	
	// Book does not support POST, PUT, or PATCH. If you want to fix a Book, delete it and re-add it through Author
	
	// Sixth endpoint: DELETE
	@DeleteMapping("/{isbn}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable String isbn) {
		return new ResponseEntity<BookResponse>(new BookResponse(__booktownService.deleteBook(isbn)), HttpStatus.NO_CONTENT);
	}
	
	// Class-level Exception Handling methods
	// Here we add a 405 handler as we are not supporting all HTTP methods
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc) {
		return new ResponseEntity<String>("Invalid method provided to Book Controller", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(value = org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleTypeMismatchException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException exc) {
		return new ResponseEntity<String>("Invalid type for Book Id, must be an Integer", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = BooktownEntityNotFoundException.class)
	public ResponseEntity<?> handleBookNotFoundException(BooktownEntityNotFoundException exc) {
		return new ResponseEntity<String>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BooktownInternalException.class) 
	public ResponseEntity<?> handleThrowable(BooktownInternalException exc) {
		return new ResponseEntity<String>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = java.lang.Throwable.class) 
	public ResponseEntity<?> handleThrowable(java.lang.Throwable t) {
		return new ResponseEntity<String>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

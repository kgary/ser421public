package edu.asu.ser421.booktown.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.asu.ser421.booktown.api.model.Author;
import edu.asu.ser421.booktown.api.model.AuthorRequest;
import edu.asu.ser421.booktown.services.AuthorService;

@RequestMapping("/authors")
@RestController
public class AuthorController {
	private AuthorService __authorService = AuthorService.getInstance();
	
	//first endpoint, return a collection of authors
	@GetMapping
	public List<Author> returnAuthors() {
		return __authorService.getAuthors();
	}
	
	//second endpoint, return a specific Author by id
	@GetMapping("/{id}")
	public Author returnAuthor(@PathVariable Integer id) {
		return __authorService.getAuthor(id);
	}
	
	// third endpoint, create an Author via POST
	@RequestMapping(method = RequestMethod.POST)
	public void createAuthor(@RequestBody AuthorRequest authorRequest) {
		__authorService.createAuthor(authorRequest.getLastName(), authorRequest.getFirstName(), authorRequest.getBooks());
	}
	
	// Put is interesting in that we either create a new Author like POST, or update an existing one
	// fourth endpoint, Insert or Update Author
	@PutMapping
	public void insertOrUpdateAuthor(@RequestBody AuthorRequest authorRequest) {
		// returns the int id of the newly created Author, or -1 if it failed
		__authorService.createAuthor(authorRequest.getLastName(), authorRequest.getFirstName(), authorRequest.getBooks());
	}
	@PutMapping("/{id}")
	public void insertOrUpdateAuthor(@PathVariable Integer id, @RequestBody AuthorRequest authorRequest) {
		// see if there is an Author with this id
		// returns boolean, if false - error
		__authorService.updateAuthor(new Author(id, authorRequest));
	}
	
	// fifth endpoint, PATCH
	@PatchMapping("/{id}")
	public void modifyAuthor(@PathVariable Integer id, @RequestBody AuthorRequest partialAuthor) {
		// in Patch, we only expect one or more attributes to be updated, not the whole object.
		// this returns a boolean
		__authorService.modifyAuthor(new Author(id, partialAuthor));
	}
	// Sixth endpoint: DELETE
	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable Integer id) {
		// returns a boolean
		__authorService.deleteAuthor(id);
	}
}

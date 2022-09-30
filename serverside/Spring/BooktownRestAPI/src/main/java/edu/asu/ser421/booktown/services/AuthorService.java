package edu.asu.ser421.booktown.services;

import java.util.List;

import edu.asu.ser421.booktown.api.model.Author;
import edu.asu.ser421.booktown.api.model.exceptions.AuthorNotFoundException;
import edu.asu.ser421.booktown.services.impl.DummyAuthorServiceImpl;

public interface AuthorService {
	public static final int CREATE_AUTHOR_ERROR = -1;
	
	public static AuthorService getInstance() {
		// this would usually be replaced by a full factory pattern or something like that
		return new DummyAuthorServiceImpl();
	}
	
	public List<Author> getAuthors();
	public Author getAuthor(int id) throws AuthorNotFoundException;
	public Author createAuthor(String lastName, String firstName, String[] titles);
	public Author updateAuthor(Author author) throws AuthorNotFoundException;
	public Author modifyAuthor(Author author) throws AuthorNotFoundException;
	public Author deleteAuthor(int id) throws AuthorNotFoundException;
}

package edu.asu.ser421.booktown.services;

import java.util.List;

import edu.asu.ser421.booktown.model.Author;
import edu.asu.ser421.booktown.model.Book;
import edu.asu.ser421.booktown.model.exceptions.BooktownEntityNotFoundException;
import edu.asu.ser421.booktown.services.impl.MockBooktownServiceImpl;

public interface BooktownService {
	public static final int CREATE_AUTHOR_ERROR = -1;
	
	public static BooktownService getInstance() {
		// this would usually be replaced by a full factory pattern or something like that
		return new MockBooktownServiceImpl();
	}
	
	// Author related
	public List<Author> getAuthors();
	public Author getAuthor(int id) throws BooktownEntityNotFoundException;
	public Author createAuthor(String lastName, String firstName, List<Book> titles);
	public Author updateAuthor(Author author) throws BooktownEntityNotFoundException;
	public Author modifyAuthor(Author author) throws BooktownEntityNotFoundException;
	public Author deleteAuthor(int id) throws BooktownEntityNotFoundException;
	
	// Book related
	public List<Book> getBooks();
	public Book getBook(int id) throws BooktownEntityNotFoundException;
	public Book deleteBook(String isbn) throws BooktownEntityNotFoundException;
}

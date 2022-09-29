package edu.asupoly.ser422.restexample.services;

import java.util.List;

import edu.asupoly.ser422.restexample.model.Author;
import edu.asupoly.ser422.restexample.model.Book;
import edu.asupoly.ser422.restexample.model.Subject;

// we'll build on this later
public interface BooktownService {
	// Author methods
    public List<Author> getAuthors();
    public Author getAuthor(int id);
    public boolean deleteAuthor(int authorId);
    public int createAuthor(String lname, String fname);
    public boolean updateAuthor(Author author);
    
    // Book methods
    public List<Book> getBooks();
    public Book getBook(int id);
    public int createBook(String title, int aid, int sid);
    public Author findAuthorOfBook(int bookId);
    
    // Subject methods
    public List<Subject> getSubjects();
    public Subject getSubject(int id);
    public List<Book> findBooksBySubject(int subjectId);
}

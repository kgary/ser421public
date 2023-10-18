package edu.asu.ser421.booktown.services;

import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.ser421.booktown.model.Author;
import edu.asu.ser421.booktown.model.Book;
import edu.asu.ser421.booktown.model.exceptions.BooktownEntityNotFoundException;
import edu.asu.ser421.booktown.repository.AuthorRepository;
import edu.asu.ser421.booktown.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
//    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);
    
    // Book related
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBook(String isbn) throws BooktownEntityNotFoundException {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new BooktownEntityNotFoundException("No Book found with ISBN " + isbn);
        }
    }
    
    public Book deleteBook(String isbn) throws BooktownEntityNotFoundException {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);

        if (optionalBook.isPresent()) {
            Book bookToDelete = optionalBook.get();
            Author authorOfBook = bookToDelete.getAuthor();

            // If the book is associated with an author, remove the reference from the author side
            if (authorOfBook != null) {
                authorOfBook.getBooks().remove(bookToDelete);
            }

            bookRepository.delete(bookToDelete);
            return bookToDelete;
        } else {
            throw new BooktownEntityNotFoundException("No Book found with ISBN " + isbn);
        }
    }
}

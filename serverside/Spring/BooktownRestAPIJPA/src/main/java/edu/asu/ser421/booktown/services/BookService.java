package edu.asu.ser421.booktown.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.asu.ser421.booktown.model.Author;
import edu.asu.ser421.booktown.model.Book;
import edu.asu.ser421.booktown.model.exceptions.BooktownEntityNotFoundException;
import edu.asu.ser421.booktown.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    
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

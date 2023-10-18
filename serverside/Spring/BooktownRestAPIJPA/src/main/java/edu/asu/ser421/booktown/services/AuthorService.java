package edu.asu.ser421.booktown.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.ser421.booktown.model.Author;
import edu.asu.ser421.booktown.model.Book;
import edu.asu.ser421.booktown.model.exceptions.BooktownEntityNotFoundException;
import edu.asu.ser421.booktown.model.exceptions.BooktownInternalException;
import edu.asu.ser421.booktown.repository.AuthorRepository;

@Service
public class AuthorService {
	
	private final AuthorRepository authorRepository;
	
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);
	
	// Author related
	public List<Author> getAuthors() {
		return authorRepository.findAll();
	};
	
	public Author getAuthor(int id) throws BooktownEntityNotFoundException {
		Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            throw new BooktownEntityNotFoundException();
        }
	};
	
	@Transactional
	public Author createAuthor(String lastName, String firstName, List<Book> titles) {
		try {
	        Author rval = new Author();
	        rval.setLastName(lastName);
	        rval.setFirstName(firstName);
	        // Associate each book with the author before saving
	        for (Book book : titles) {
	            book.setAuthor(rval);
	        }
	        rval.setBooks(titles);
	        return authorRepository.save(rval);
	    } catch (Exception e) {
	    	log.error("Error creating author", e);
	        throw new BooktownInternalException("Internal Service Error, unable to create Author", e);
	    }
	};
	
	public Author updateAuthor(Author author) throws BooktownEntityNotFoundException, BooktownInternalException {
	    try {
	        // Check if the author with the given ID exists in the database
	        if (authorRepository.existsById(author.getAuthorID())) {
	            // Save updates the entity if it already exists
	            return authorRepository.save(author);
	        } else {
	            throw new BooktownEntityNotFoundException("Author with ID " + author.getAuthorID() + " not found.");
	        }
	    } catch (Exception e) {
//	    	log.error("Error updating author with ID " + author.getAuthorID(), e);
	        throw new BooktownInternalException("Internal error while updating author.");
	    }
	}
	
	public Author modifyAuthor(Author partialAuthor) throws BooktownEntityNotFoundException, BooktownInternalException {
	    try {
	        Optional<Author> optionalAuthor = authorRepository.findById(partialAuthor.getAuthorID());

	        if (optionalAuthor.isPresent()) {
	            Author existingAuthor = optionalAuthor.get();
	            
	            if (partialAuthor.getFirstName() != null) {
	                existingAuthor.setFirstName(partialAuthor.getFirstName());
	            }
	            if (partialAuthor.getLastName() != null) {
	                existingAuthor.setLastName(partialAuthor.getLastName());
	            }
	            if (partialAuthor.getBooks() != null) {
	                existingAuthor.setBooks(partialAuthor.getBooks());
	            }

	            // Save updates the entity
	            return authorRepository.save(existingAuthor);
	        } else {
	            throw new BooktownEntityNotFoundException("Author with ID " + partialAuthor.getAuthorID() + " not found.");
	        }
	    } catch (Exception e) {
	        // Logging the exception is a better practice than printStackTrace
//	        log.error("Error modifying author with ID " + partialAuthor.getAuthorID(), e);
	        throw new BooktownInternalException("Internal error while modifying author.");
	    }
	}
	
	public Author deleteAuthor(int id) throws BooktownEntityNotFoundException, BooktownInternalException {
	    try {
	        Optional<Author> optionalAuthor = authorRepository.findById(id);

	        if (optionalAuthor.isPresent()) {
	            Author authorToDelete = optionalAuthor.get();
	            authorToDelete.getBooks().clear();
	            authorRepository.delete(authorToDelete);
	            return authorToDelete;
	        } else {
	            throw new BooktownEntityNotFoundException("Author with ID " + id + " not found.");
	        }
	    } catch (Exception e) {
	        // Logging the exception is a better practice than printStackTrace
//	        log.error("Error deleting author with ID " + id, e);
	        throw new BooktownInternalException("Internal error while deleting author.");
	    }
	}
	
}

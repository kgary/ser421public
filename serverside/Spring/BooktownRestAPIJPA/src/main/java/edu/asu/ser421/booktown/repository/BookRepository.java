package edu.asu.ser421.booktown.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.asu.ser421.booktown.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	Optional<Book> findByIsbn(String isbn);
}

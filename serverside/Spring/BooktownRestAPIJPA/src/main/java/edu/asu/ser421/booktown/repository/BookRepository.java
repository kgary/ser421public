package edu.asu.ser421.booktown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.asu.ser421.booktown.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}

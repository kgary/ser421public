package edu.asu.ser421.booktown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.asu.ser421.booktown.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}

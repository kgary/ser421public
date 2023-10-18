package edu.asu.ser421.booktown;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import edu.asu.ser421.booktown.model.Author;
import edu.asu.ser421.booktown.model.Book;
import edu.asu.ser421.booktown.repository.AuthorRepository;

@Configuration
public class DataLoader{
	
	@Bean
    CommandLineRunner booktownItemsCommandLineRunner(AuthorRepository authorRepository) {
        return args -> {
            // booktown items initialization
        	List<Book> frostBooks  = new ArrayList<>();
            List<Book> fowlerBooks = new ArrayList<>();
            
            frostBooks.add(new Book("123456789", "The Road Not Taken", null));
            frostBooks.add(new Book("987654321", "Stopping by the Woods on a Snowy Evening", null));
            
            fowlerBooks.add(new Book("111111111", "UML Distilled", null));
            fowlerBooks.add(new Book("222222222", "Analysis Patterns", null));
            fowlerBooks.add(new Book("333333333", "Refactoring", null));
            
            Author frost = new Author(1, "Frost", "Robert", frostBooks);
            Author fowler = new Author(2, "Fowler", "Martin", fowlerBooks);
            Author gary = new Author(3, "Gary", "Kevin", new ArrayList<>());
            
            frostBooks.forEach(book -> book.setAuthor(frost));
            fowlerBooks.forEach(book -> book.setAuthor(fowler));
            
            authorRepository.saveAll(Arrays.asList(frost, fowler, gary));
        };
        
	}
}


package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class BookRepository {

    private static ArrayList<Book> dummyBooks = new ArrayList<>();

    static {
        dummyBooks.addAll(Arrays.asList(
                new Book("123456789", "The Road Not Taken", 0),
                new Book("987654321", "To Kill a Mockingbird", 1),
                new Book("456789123", "The Great Gatsby", 2)
        ));
    }

    public List<Book> getBooks() {
        return dummyBooks;
    }

    public Book getBookByISBN(String isbn) {
        for (Book book : dummyBooks) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Book save(String isbn, String title, int authorId) {
        Book newBook = new Book(isbn, title, authorId);
        dummyBooks.add(newBook);
        return newBook;
    }

    public static ArrayList<Book> getBooksByAuthorId(int id) {
        ArrayList<Book> bookList = new ArrayList<>();
        for (Book book : dummyBooks) {
            if (book.getAuthorId() == id) {
                bookList.add(book);
            }
        }
        return bookList;
    }

}

package edu.asu.ser421.booktown.api.modelhelpers;

import java.util.ArrayList;
import java.util.List;

import edu.asu.ser421.booktown.model.Book;

public class BookLink {
	private String __url;
	private String __isbn;
	
	public BookLink() {
		super();
	}

	public BookLink(Book book) {
		__isbn = book.getIsbn();
	}

	public String getIsbn() {
		return __isbn;
	}
	public String getLink() {
		return __url;
	}
	public void setLink(String url) {
		this.__url = url;
	}
	public void setIsbn(String isbn) {
		__isbn = isbn;
	}
	// static utility method
	public static List<BookLink> convertBooksToLinks(List<Book> books) {
		List<BookLink> bookLinks = new ArrayList<BookLink>();

		if (books != null ) {
			for (Book b : books) {
				bookLinks.add(new BookLink(b));
			}
		}

		return bookLinks;
	}
}


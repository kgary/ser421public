package edu.asu.ser421.booktown.api.modelhelpers;

import java.util.ArrayList;
import java.util.List;

import edu.asu.ser421.booktown.model.Book;

public class BookResponse {
	private String __url;
	private String __authorLink;
	private Book __book;
	
	public BookResponse() {
		super();
	}

	public BookResponse(Book book) {
		__book = book;
		// the author link will be injected at render time
		__authorLink = null;
	}

	// These are bookResponse-specific properties
	public String getLink() {
		return __url;
	}
	public void setLink(String url) {
		this.__url = url;
	}
	
	public String getAuthorLink() {
		return __authorLink;
	}

	public void setAuthorLink(String __authorLink) {
		this.__authorLink = __authorLink;
	}
	
	// These are shadowed from Book
	public String getIsbn() {
		return __book.getIsbn();
	}
	public void setIsbn(String isbn) {
		__book.setIsbn(isbn);
	}
	public String getTitle() {
		return __book.getTitle();
	}
	public void setTitle(String title) {
		__book.setTitle(title);
	}
	public int getAuthorId() {
		return __book.getAuthorId();
	}
	public void setAuthorId(int authorId) {
		__book.setAuthorId(authorId);
	}
	
	// static utility method
	public static List<BookResponse> convertBooksToResponses(List<Book> books) {
		List<BookResponse> bookResponses = new ArrayList<BookResponse>();
		
		if (books != null ) {
			for (Book b : books) {
				bookResponses.add(new BookResponse(b));
			}
		}
		return bookResponses;
	}
}

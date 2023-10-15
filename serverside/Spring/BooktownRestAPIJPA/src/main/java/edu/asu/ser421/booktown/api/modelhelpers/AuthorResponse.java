package edu.asu.ser421.booktown.api.modelhelpers;

import java.util.ArrayList;
import java.util.List;

import edu.asu.ser421.booktown.model.Author;

public class AuthorResponse {
	private String __url;
	private List<BookLink> __bookLinks;
	private Author __author;
	
	public AuthorResponse() {
		super();
	}

	public AuthorResponse(Author author) {
		__author = author;
		// we need to generate the Book Links from the Books.
		setBookLinks(BookLink.convertBooksToLinks(author.getBooks()));
	}

	// These are AuthorResponse-specific properties
	public String getLink() {
		return __url;
	}
	public void setLink(String url) {
		this.__url = url;
	}
	
	public List<BookLink> getBookLinks() {
		return __bookLinks;
	}

	public void setBookLinks(List<BookLink> __bookLinks) {
		this.__bookLinks = __bookLinks;
	}
	
	// These are shadowed From Author
	public int getAuthorID() {
		return __author.getAuthorID();
	}
	public String getLastName() {
		return __author.getLastName();
	}
	public String getFirstName() {
		return __author.getFirstName();
	}
	// But we don't include Books since we are shadowing using our BookLink output format!
	
	public void setAuthorId(int id) {
		__author.setAuthorId(id);
	}
	public void setLastName(String lname) {
		__author.setLastName(lname);
	}
	public void setFirstName(String fname) {
		__author.setFirstName(fname);
	}
	
	// static utility method
	public static List<AuthorResponse> convertAuthorsToResponses(List<Author> authors) {
		List<AuthorResponse> authorResponses = new ArrayList<AuthorResponse>();
		
		if (authors != null ) {
			for (Author a : authors) {
				authorResponses.add(new AuthorResponse(a));
			}
		}
		return authorResponses;
	}
}

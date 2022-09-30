package edu.asu.ser421.booktown.api.modelhelpers;

import java.util.ArrayList;
import java.util.List;

import edu.asu.ser421.booktown.api.model.Author;

public class AuthorResponse extends Author {
	private String __url;
	
	public AuthorResponse() {
		super();
	}

	public AuthorResponse(Author author) {
		super(author.getAuthorID(), author.getLastName(), author.getFirstName(), author.getBooks());
	}

	public String getLink() {
		return __url;
	}
	public void setLink(String url) {
		this.__url = url;
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

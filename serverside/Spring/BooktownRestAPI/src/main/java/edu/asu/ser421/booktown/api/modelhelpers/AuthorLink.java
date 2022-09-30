package edu.asu.ser421.booktown.api.modelhelpers;

import java.util.ArrayList;
import java.util.List;

import edu.asu.ser421.booktown.api.model.Author;

public class AuthorLink {
	private String __url;
	private int __id;
	
	public AuthorLink() {
		super();
	}

	public AuthorLink(Author author) {
		__id = author.getAuthorID();
	}

	public int getAuthorID() {
		return __id;
	}
	public String getLink() {
		return __url;
	}
	public void setLink(String url) {
		this.__url = url;
	}
	public void setAuthorId(int id) {
		__id = id;
	}
	// static utility method
	public static List<AuthorLink> convertAuthorsToLinks(List<Author> authors) {
		List<AuthorLink> authorLinks = new ArrayList<AuthorLink>();

		if (authors != null ) {
			for (Author a : authors) {
				authorLinks.add(new AuthorLink(a));
			}
		}

		return authorLinks;
	}
}


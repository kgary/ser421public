package edu.asu.ser421.booktown.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.asu.ser421.booktown.api.model.Author;
import edu.asu.ser421.booktown.services.AuthorService;

// Normally thsi would be an interface and we'd allow variability in implementation
public class DummyAuthorServiceImpl implements AuthorService {
	// This is a mock object for now
	private ArrayList<Author> dummyAuthors = new ArrayList<Author>();
	
	public DummyAuthorServiceImpl() {
		String[] frostTitles = { "The Road Not Taken", "Stopping by the Woods on a Snowy Evening" };
		String[] fowlerTitles = { "UML Distilled", "Analysis Patterns", "Refactoring" };
		dummyAuthors.addAll(Arrays.asList(
				new Author(0, "Frost", "Robert", frostTitles),
				new Author(1, "Fowler", "Martin", fowlerTitles),
				new Author(2, "Gary", "Kevin", new String[0])
		));
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> getAuthors() {
		// should do a deep clone - Author needs a clone method
		return (List<Author>) dummyAuthors.clone();
	}
	public Author getAuthor(int id) {
		for (Author a : dummyAuthors) {
			if (a.getAuthorID() == id) return a;
		}
		return null;
	}
	
	// I like service method to return something to indicate success or failure. In this case the ID
	public int createAuthor(String lastName, String firstName, String[] titles) {
		try {
			dummyAuthors.add(new Author(dummyAuthors.size(), lastName, firstName, titles));
			return dummyAuthors.size()-1;
		} catch (Throwable t) {  // I like service code to be very defensive, including unchecked exceptions.
			return AuthorService.CREATE_AUTHOR_ERROR;
		}
	}

	@Override
	public boolean updateAuthor(Author author) {
		int i = 0;
		for (;i < dummyAuthors.size() && dummyAuthors.get(i).getAuthorID() != author.getAuthorID(); i++)
			;;;
		if (i != dummyAuthors.size()) {
			dummyAuthors.set(i, author);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean modifyAuthor(Author partialAuthor) {
		// the client contract is to only provide the updated parts, so we can test for null here
		// First we must find the Author though
		int i = 0;
		for (;i < dummyAuthors.size() && dummyAuthors.get(i).getAuthorID() != partialAuthor.getAuthorID(); i++)
			;;;
		if (i != dummyAuthors.size()) {
			Author patchAuthor = dummyAuthors.get(i);
			// now go property-by-property through AuthorRequest
			if (partialAuthor.getFirstName() != null) {
				patchAuthor.setFirstName(partialAuthor.getFirstName());
			}
			if (partialAuthor.getLastName() != null) {
				patchAuthor.setLastName(partialAuthor.getLastName());
			}
			if (partialAuthor.getBooks() != null) {
				patchAuthor.setBooks(partialAuthor.getBooks());
			}
			
			return true;
 		} else {
 			return false;
 		}
	}
	
	public boolean deleteAuthor(int id) {
		// First we must find the Author though
		int i = 0;
		for (;i < dummyAuthors.size() && dummyAuthors.get(i).getAuthorID() != id; i++)
			;;;
		if (i != dummyAuthors.size()) {
			dummyAuthors.remove(i);
			return true;
		} else {
			return false;
		}
	}
}

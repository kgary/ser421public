package edu.asupoly.ser421.booktown.services.impl;

import java.util.List;
import java.util.ArrayList;

import edu.asupoly.ser421.booktown.model.Author;
import edu.asupoly.ser421.booktown.services.BooktownService;

//A simple impl of interface BooktownService
public class SimpleBooktownServiceImpl implements BooktownService {
	private final static String[] fnames = {"Laura", "Hillary", "Martha", "Ivana", "Jackie",};
	private final static String[] lnames = {"Bush", "Clinton", "Washington", "Trump", "Kennedy"};
	
	private ArrayList<Author> __authors = null;

	// Only instantiated by factory?
	public SimpleBooktownServiceImpl() {
		__authors = new ArrayList<Author>();
		for (int i = 0; i < fnames.length; i++) {
			__authors.add(new Author(i, fnames[i], lnames[i]));
		}
	}

	public List<Author> getAuthors() {
		List<Author> deepClone = new ArrayList<Author>();
		for (Author a : __authors) {
			deepClone.add(new Author(a.getAuthorID(), a.getFirstName(), a.getLastName()));
		}
		return deepClone;
	}

	public int createAuthor(String lname, String fname) {
		int max = -1;
		for (Author a : __authors) {
			if (a.getAuthorID() > max) {
				max = a.getAuthorID();
			}
		}
		__authors.add(new Author(max, fname, lname));
		return max;
	}

	public boolean deleteAuthor(int authorId) {
		try {
			return (__authors.remove(authorId) != null);
		} catch (Exception exc) {
			return false;
		}
	}
}

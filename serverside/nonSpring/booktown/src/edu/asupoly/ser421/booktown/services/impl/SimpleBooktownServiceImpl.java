package edu.asupoly.ser421.booktown.services.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import edu.asupoly.ser421.booktown.model.Author;
import edu.asupoly.ser421.booktown.services.BooktownService;

//A simple impl of interface BooktownService
public class SimpleBooktownServiceImpl implements BooktownService {
	private final static String[] fnames = {"Laura", "Hillary", "Martha", "Melania", "Jackie",};
	private final static String[] lnames = {"Bush", "Clinton", "Washington", "Trump", "Kennedy"};
	
	private Map<Integer, Author> __authors = null;

	// Only instantiated by factory?
	public SimpleBooktownServiceImpl() {
		__authors = new HashMap<Integer, Author>();
		for (int i = 0; i < fnames.length; i++) {
			__authors.put(i, new Author(i, fnames[i], lnames[i]));
		}
	}

	public List<Author> getAuthors() {
		List<Author> authors = new ArrayList<Author>(__authors.values());

		return authors;
	}

	public int createAuthor(String lname, String fname) {
		int max = -1;
		for (Author a : __authors.values()) {
			if (a.getAuthorID() > max) {
				max = a.getAuthorID();
			}
		}
		__authors.put(max+1, new Author(max+1, fname, lname));
		return max+1;
	}

	public boolean deleteAuthor(int authorId) {
		try {
			return (__authors.remove(authorId) != null);
		} catch (Exception exc) {
			return false;
		}
	}
}

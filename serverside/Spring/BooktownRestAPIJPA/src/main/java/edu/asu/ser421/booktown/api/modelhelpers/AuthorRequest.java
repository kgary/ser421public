package edu.asu.ser421.booktown.api.modelhelpers;

import java.util.ArrayList;
import java.util.List;

import edu.asu.ser421.booktown.model.Book;

public class AuthorRequest {
	public AuthorRequest() {		
	}
	public AuthorRequest(String lname, String fname, List<Book> titles) {
		__lastName  = lname;
		__firstName = fname;
		__books = titles;
	}
	public String getLastName() {
		return __lastName;
	}
	public String getFirstName() {
		return __firstName;
	}
	public List<Book> getBooks() {
		return __books;
	}
	
	public void setLastName(String lname) {
		__lastName = lname;
	}
	public void setFirstName(String fname) {
		__firstName = fname;
	}
	public void setBooks(List<Book> titles) {
		__books = titles;
	}
	
	private String __lastName;
	private String __firstName;
	private List<Book> __books = new ArrayList<>();;
}

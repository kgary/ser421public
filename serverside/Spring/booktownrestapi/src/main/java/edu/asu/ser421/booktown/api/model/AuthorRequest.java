package edu.asu.ser421.booktown.api.model;

public class AuthorRequest {
	public AuthorRequest() {		
	}
	public AuthorRequest(String lname, String fname) {
		__lastName  = lname;
		__firstName = fname;
	}
	public String getLastName() {
		return __lastName;
	}
	public String getFirstName() {
		return __firstName;
	}
	public String[] getBooks() {
		String[] rval = new String[__titles.length];
		System.arraycopy(__titles,  0,  rval, 0, __titles.length);
		return (rval);
	}
	
	public void setLastName(String lname) {
		__lastName = lname;
	}
	public void setFirstName(String fname) {
		__firstName = fname;
	}
	public void setBooks(String[] titles) {
		if (titles != null) {
			__titles = new String[titles.length];
			System.arraycopy(titles,  0,  __titles, 0, titles.length);
		}
	}
	
	private String __lastName;
	private String __firstName;
	private String[] __titles;
}
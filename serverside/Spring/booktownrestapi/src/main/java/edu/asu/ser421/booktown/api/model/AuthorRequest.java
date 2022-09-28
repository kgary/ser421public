package edu.asu.ser421.booktown.api.model;

public class AuthorRequest {
	public AuthorRequest() {		
	}
	public AuthorRequest(String lname, String fname, String[] books) {
		__lastName  = lname;
		__firstName = fname;
		__titles = books;
	}
	public String getLastName() {
		return __lastName;
	}
	public String getFirstName() {
		return __firstName;
	}
	public String[] getBooks() {
		if (__titles == null) {  // defensive programming check
			__titles = new String[0];
		}
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
		} else {
			__titles = new String[0];
		}
	}
	
	private String __lastName;
	private String __firstName;
	private String[] __titles;
}

package edu.asu.ser421.booktown.api.model;

public class Author {
	public Author() {
		
	}
	public Author(int id, AuthorRequest ar) {
		this(id, ar.getLastName(), ar.getFirstName(), ar.getBooks());
	}
	public Author(int id, String lname, String fname, String[] titles) {
		__id = id;
		__lastName  = lname;
		__firstName = fname;
		this.setBooks(titles);
	}
	public int getAuthorID() {
		return __id;
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
	
	public void setAuthorId(int id) {
		__id = id;
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
	
	// Best practices - implement from java.lang.Object
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Author)) return false;
		// just easier
		Author a = (Author) obj;
		
		boolean rval = a.getLastName().equals(this.getLastName()) && a.getFirstName().equals(this.getFirstName());
		
		// now check the books
		if (rval && (rval = (a.getBooks() != null && a.getBooks().length == this.getBooks().length))) {
			for (int i = 0; rval && i < this.getBooks().length; i++) {
				rval = a.getBooks()[i].equals(this.getBooks()[i]);
			}
		}
		return rval;
	}

	private int    __id;
	private String __lastName;
	private String __firstName;
	private String[] __titles;
}

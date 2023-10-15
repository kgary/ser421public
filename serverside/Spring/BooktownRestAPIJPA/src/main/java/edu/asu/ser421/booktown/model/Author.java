package edu.asu.ser421.booktown.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.asu.ser421.booktown.api.modelhelpers.AuthorRequest;

@Entity
@Table(name = "authors")
public class Author {
	public Author() {
		
	}
	public Author(int id, AuthorRequest ar) {
		this(id, ar.getLastName(), ar.getFirstName(), ar.getBooks());
	}
	public Author(int id, String lname, String fname, List<Book> titles) {
		__id = id;
		__lastName  = lname;
		__firstName = fname;
		__books = titles;
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
	public List<Book> getBooks() {
		return __books;
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
	public void setBooks(List<Book> titles) {
		__books = titles;
	}
	public boolean removeBook(Book b) {
		return __books.remove(b);
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
		if (rval && (rval = (a.getBooks() != null && a.getBooks().size() == this.getBooks().size()))) {
			for (int i = 0; rval && i < this.getBooks().size(); i++) {
				rval = a.getBooks().get(i).equals(this.getBooks().get(i));
			}
		}
		return rval;
	}
	
	@Id
	private int    __id;
	
	@Column(name = "last_name")
	private String __lastName;
	
	@Column(name = "first_name")
	private String __firstName;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> __books = new ArrayList<Book>();
}

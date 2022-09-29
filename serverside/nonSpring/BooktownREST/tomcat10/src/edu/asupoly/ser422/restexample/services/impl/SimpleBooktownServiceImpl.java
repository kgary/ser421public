package edu.asupoly.ser422.restexample.services.impl;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import edu.asupoly.ser422.restexample.model.Author;
import edu.asupoly.ser422.restexample.model.Book;
import edu.asupoly.ser422.restexample.model.Subject;

//A simple impl of interface BooktownService
public class SimpleBooktownServiceImpl extends ABooktownServiceImpl {
	
	// Author section
	private final static String[] fnames = {"Laura", "Hillary", "Jackie"};
	private final static String[] lnames = {"Bush", "Clinton", "Kennedy"};
	private Set<Author> __authors = null;

	public List<Author> getAuthors() {
		List<Author> deepClone = new ArrayList<Author>();
		for (Author a : __authors) {
			deepClone.add(new Author(a.getAuthorId(), a.getLastName(), a.getFirstName()));
		}
		return deepClone;
	}

	public int createAuthor(String lname, String fname) {	
		int authorId = generateKey(1,99999);
		// 10 retries 
		for (int i = 0; i < 10 && !(__authors.add(new Author(authorId, lname, fname))); ) {
			authorId = generateKey(1,99999);
		}
		return authorId;
    }

	public boolean deleteAuthor(int authorId) {
		boolean rval = false;
		try {
			// Find any Books pointing at this author
			Set<Book> books = new LinkedHashSet<Book>();
			for (Book b : __books) {
				if (b.getAuthorId() == authorId) {
					b.setAuthorId(-1);  // I guess -1 will mean marked for deletion
					books.add(b);
				}
			}

			Author a = getAuthor(authorId);
			if (a != null) {
				rval = __authors.remove(a);
			}
			if (!rval) {
				// if we couldn't remove that book we have to undo the books above,
				// which is why we hung onto them!
				for (Book b : books) {
					b.setAuthorId(authorId);
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			rval = false;
		}
		return rval;
	}
	
	@Override
	public Author getAuthor(int id) {
		for (Author a : __authors) {
			if (a.getAuthorId() == id) {
				return a;
			}
		}
		return null;
	}

	@Override
	public boolean updateAuthor(Author author) {
		boolean rval = false;
		for (Author a : __authors) {
			if (a.getAuthorId() == author.getAuthorId()) {
				rval = true;
				a.setFirstName(author.getFirstName());
				a.setLastName(author.getLastName());
			}
		}
		return rval;
	}
	
    // Book section
	private final static String[] titles = {"Sisters First", "My Turn", "Four Days"};
	private Set<Book> __books = null;

    public List<Book> getBooks() {
		List<Book> deepClone = new ArrayList<Book>();
		for (Book b : __books) {
			deepClone.add(new Book(b.getBookId(), b.getTitle(), b.getAuthorId(), b.getSubjectId()));
		}
		return deepClone;
    }
    public Book getBook(int id) {
		for (Book b : __books) {
			if (b.getBookId() == id) {
				return b;
			}
		}
    		return null;	
    }
    
    public int createBook(String title, int aid, int sid) {
		int bookId = generateKey(1,99999);
		// 10 retries 
		for (int i = 0; i < 10 && !(__books.add(new Book(bookId, title, aid, sid))); ) {
			bookId = generateKey(1,99999);
		}
		return bookId;
    }
    
    public Author findAuthorOfBook(int bookId) {
    		Author a = null;
    		Book b = getBook(bookId);
    		if (b != null) {
    			a = getAuthor(b.getAuthorId());
    		}
    		return a;
    }
    
    // Subject section
	private final static String[] subjects = {"Humor", "Politics", "Drama"};
	private final static String[] locations = {"Midland, TX", "Little Rock, AR", "Dallas, TX"};
	private Set<Subject> __subjects = null;
	
	public int createSubject(String subject, String location) {
		int subjectId = generateKey(1,99999);
		// 10 retries if we have a key clash
		for (int i = 0; i < 10 && !(__subjects.add(new Subject(subjectId, subject, location))); ) {
			subjectId = generateKey(1,99999);
		}
		return subjectId;
	}
	
    public List<Subject> getSubjects() {
		List<Subject> deepClone = new ArrayList<Subject>();
		for (Subject s : __subjects) {
			deepClone.add(new Subject(s.getSubjectId(), s.getSubject(), s.getLocation()));
		}
		return deepClone;
    }
    public Subject getSubject(int id) {
    		for (Subject s : __subjects) {
    			if (s.getSubjectId() == id) {
    				return s;
    			}
    		}
    		return null;
    }
    public List<Book> findBooksBySubject(int subjectId) {
    		return null;
    }
    
	// Only instantiated by factory?
	public SimpleBooktownServiceImpl() {
		__authors = new LinkedHashSet<Author>();
		__books = new LinkedHashSet<Book>();
		__subjects = new LinkedHashSet<Subject>();
		for (int i = 0; i < fnames.length; i++) {
			int aid = createAuthor(lnames[i], fnames[i]);
			int sid = createSubject(subjects[i], locations[i]);
			createBook(titles[i], aid, sid);
		}
	}
}

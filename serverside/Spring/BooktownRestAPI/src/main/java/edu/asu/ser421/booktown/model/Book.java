package edu.asu.ser421.booktown.model;

public class Book {
	private String isbn;   // always 9
	private String title;
	private int authorId;
	
	public Book() {	
	}
	
	public Book(String isbn, String t, int aid) {
		this.isbn = isbn;
		this.title = t;
		this.authorId = aid;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		if (isbn.length() == 9) {
			this.isbn = isbn;
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
}

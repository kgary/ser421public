package edu.asu.ser421.booktown.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@Column(length = 9)
	private String isbn;   // always 9
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
	private Author authorId;
	
	public Book() {	
	}
	
	public Book(String isbn, String t, Author aid) {
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
	public Author getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}
}

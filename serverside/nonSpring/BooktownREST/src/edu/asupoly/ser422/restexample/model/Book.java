package edu.asupoly.ser422.restexample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	private int id;
	private String title;
	private int authorId;
	private int subjectId;
	
	public Book() {	
	}
	
	public Book(int id, String t, int aid, int sid) {
		this.id = id;
		this.title = t;
		this.authorId = aid;
		this.subjectId = sid;
	}
	
	public int getBookId() {
		return id;
	}
	public void setBookId(int id) {
		this.id = id;
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
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}

package edu.asupoly.ser422.restexample.model;

public class Subject {
	private int id;
	private String subject;
	private String location;
	
	public Subject (int id, String s, String l) {
		this.id = id; 
		subject = s;
		location = l;
	}
	public int getSubjectId() {
		return id;
	}
	public String getSubject() {
		return subject;
	}
	public String getLocation() {
		return location;
	}
}

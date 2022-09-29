package edu.asupoly.ser422.restexample.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author {
	public Author() {}
	
	public Author(int id, String lname, String fname) {
		__id = id;
		__lastName  = lname;
		__firstName = fname;
	}
	public int getAuthorId() {
		return __id;
	}
	public String getLastName() {
		return __lastName;
	}
	public String getFirstName() {
		return __firstName;
	}

	public void setAuthorId(int __id) {
		this.__id = __id;
	}
	public void setLastName(String __lastName) {
		this.__lastName = __lastName;
	}
	public void setFirstName(String __firstName) {
		this.__firstName = __firstName;
	}
	
	public String toString() {
		return "Author ID " + getAuthorId() + ", lastName " + getLastName() + ", firstName " + getFirstName();
	}
	private int    __id;
	private String __lastName;
	private String __firstName;
}

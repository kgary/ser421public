package edu.asu.ser421.rest.grocery.model;

// This version will manually construct the bean with no annotation help
public class Producer {

	// Spring does setter-based injection, so we always need the presence of our default constructor
	public Producer() {}
	
	public Producer(String abbreviation, String name, String address) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.address = address;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String abbreviation;
	private String name;
	private String address;
}

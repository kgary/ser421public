package edu.asu.ser421.rest.grocery.model;

import java.util.List;

// This version will manually construct the bean with no annotation help
public class GroceryItem {

	// Spring does setter-based injection, so we always need the presence of our default constructor
	public GroceryItem() {}
	
	public GroceryItem(String id, String name, GroceryType type, float p, List<Producer> producers) {
		this.id = id;
		this.item = name;
		this.groceryType = type;
		this.price = p;
		this.producers = producers;
	}

	public static enum GroceryType {
		DAIRY, BREADS, DELI, PRODUCE
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public GroceryType getGroceryType() {
		return groceryType;
	}

	public void setGroceryType(GroceryType groceryType) {
		this.groceryType = groceryType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	private String id;
	private String item;
	private GroceryType groceryType;
	private float price;
	private List<Producer> producers;
}

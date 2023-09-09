package edu.asu.ser421.grocerydemo.model;

import javax.persistence.*;

@Entity
@Table(name = "groceryitems")
public class GroceryItem {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public GroceryType getGroceryType() {
		return groceryType;
	}

	public void setGroceryType(GroceryType type) {
		this.groceryType = type;
	}
	
	public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
	
	public GroceryItem() {
		
	}

	public GroceryItem(String id, String name, GroceryType type, float p, boolean subscribed) {
		this.id = id;
		this.name = name;
		this.groceryType = type;
		this.price = p;
		this.subscribed = subscribed;
	}
	
	@Id
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "subscribed")
	private boolean subscribed;
	
	@Enumerated(EnumType.STRING)
	private GroceryType groceryType; 
	
	public static enum GroceryType {
		DAIRY, BREADS, DELI, PRODUCE
	}
}

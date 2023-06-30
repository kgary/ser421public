package edu.asu.ser421.rest.grocery.services;

import static java.lang.Class.forName;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import edu.asu.ser421.rest.grocery.model.GroceryItem;
import edu.asu.ser421.rest.grocery.model.GroceryItem.GroceryType;

public interface GroceryServices {
	public List<GroceryItem> findAll();
	public GroceryItem findOne(String id);
	public boolean create(GroceryItem item);
	public boolean update(GroceryItem item);
	public boolean delete(String id);
	// we'll come back to Patch
	
	// custom query methods
	public List<GroceryItem> findByCategory(GroceryType category);
	
	//@Autowired
	//public static final Environment env = null;
	public static GroceryServices getGroceryService() {
		try {
			String __theServiceImpl = "edu.asu.ser421.rest.grocery.services.impl.SimpleGroceryServicesImpl"; //env.getProperty("groceryServices.impl");
			System.out.println("The service impl is " + __theServiceImpl);
			Class<GroceryServices> implClass = (Class<GroceryServices>) forName(__theServiceImpl);
			Method m = implClass.getMethod("getGroceriesService");
			return (GroceryServices) m.invoke(null); // invoking a static method
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
}

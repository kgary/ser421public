package edu.asu.ser421.rest.grocery.services;

import static java.lang.Class.forName;

import java.lang.reflect.Method;
import java.util.List;

import edu.asu.ser421.rest.grocery.exceptions.GroceryConflictException;
import edu.asu.ser421.rest.grocery.model.GroceryItem;
import edu.asu.ser421.rest.grocery.model.GroceryItem.GroceryType;
import edu.asu.ser421.rest.grocery.model.Producer;

public interface GroceryServices {
	public List<GroceryItem> findAll() throws Exception;
	public GroceryItem findOne(String id) throws Exception;
	public String create(GroceryItem item) throws Exception;	// returns the new Id
	public boolean update(GroceryItem item) throws Exception;   // true if update, false if create
	public boolean delete(String id) throws Exception;			// true if id there, false if not
	// we'll come back to Patch
	
	// custom query methods
	public List<GroceryItem> findByCategory(GroceryType category);
	
	// Methods for Grocery-Producers relationship
	public List<Producer> findAllGroceryProducers(String groceryId) throws Exception;
	// find those whose Producer address is a substring of the param
	public List<Producer> findGroceryProducersByAddress(String groceryId, String address) throws Exception;
	// create a new relationship (a new Producer) for this GroceryItem. True if successful, false if not
	public boolean createGroceryProducer(String groceryId, String producerAbbrev) throws Exception;
	// delete the relationship. True if successful, false if not
	// Note the 2 exceptions, the former is our own and indicates the grocery item is down to 1 Producer.
	public boolean deleteGroceryProducer(String groceryId, String producerAbbred) throws GroceryConflictException, Exception;
	
	// normally we would implement a configurable logic to decide our service implementation
	public static GroceryServices getGroceryService() {
		try {
			String __theServiceImpl = "edu.asu.ser421.rest.grocery.services.impl.SimpleGroceryServicesImpl";
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

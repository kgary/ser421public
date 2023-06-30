package edu.asu.ser421.rest.grocery.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import edu.asu.ser421.rest.grocery.model.GroceryItem;
import edu.asu.ser421.rest.grocery.model.GroceryItem.GroceryType;
import edu.asu.ser421.rest.grocery.services.GroceryServices;

public class SimpleGroceryServicesImpl implements GroceryServices {

	// Implementing as a Singleton. Note that this implementation needs to be thread-safe
	private static GroceryServices __theService = null;
	public static GroceryServices getGroceriesService() {
		if (__theService == null) {
			__theService = new SimpleGroceryServicesImpl();
		}
		return __theService;
	}
	private Map<String, GroceryItem> __groceryItems = null;
	
	@SuppressWarnings("serial")
	private SimpleGroceryServicesImpl() {
		//let's create some mock data
		__groceryItems = new HashMap<String, GroceryItem>() {{
				put("MLK", new GroceryItem("MLK", "milk", GroceryType.DAIRY, 3.99f));
				put("SWC", new GroceryItem("SWC", "swiss cheese", GroceryType.DAIRY, 4.49f));
				put("YOG", new GroceryItem("YOG", "yogurt", GroceryType.DAIRY, 0.99f));
				put("WHB", new GroceryItem("WHB", "wheat bread", GroceryType.BREADS, 2.79f));
				put("GRB", new GroceryItem("GRB", "garlic bread", GroceryType.BREADS, 1.99f));
				put("APL", new GroceryItem("APL", "apples", GroceryType.PRODUCE, 0.69f));
				put("BRC", new GroceryItem("BRC", "broccoli", GroceryType.PRODUCE, 1.19f));
				put("PAS", new GroceryItem("PAS", "pastrami", GroceryType.DELI, 8.99f));
				put("HAM", new GroceryItem("HAM", "ham", GroceryType.PRODUCE, 5.69f));
		}};
	}

	@Override
	public List<GroceryItem> findAll() {
		return new ArrayList<GroceryItem>(__groceryItems.values());
	}

	@Override
	public GroceryItem findOne(String id) {
		return __groceryItems.get(id);
	}

	@Override
	public boolean create(GroceryItem item) {
		boolean rval = __groceryItems.containsKey(item.getId());
		if (!rval) {  // good to create
			__groceryItems.put(item.getId(), item);
		}
		return !rval;  // flip the flag (think about it)
	}

	@Override
	public boolean update(GroceryItem item) {
		boolean rval = __groceryItems.containsKey(item.getId());
		// we are updating so we don't care what rval is, we are writing anyway
		__groceryItems.put(item.getId(), item);
		return rval; // true if update, false if create
	}

	@Override
	public boolean delete(String id) {
		boolean rval = __groceryItems.containsKey(id);
		if (rval) {
			__groceryItems.remove(id);
		}
		return rval; // true if deleted, false if not there
	}

	@Override
	public List<GroceryItem> findByCategory(GroceryType category) {
		return __groceryItems.values().stream().filter(x -> x.getGroceryType().equals(category)).collect(Collectors.toList());
	}
}

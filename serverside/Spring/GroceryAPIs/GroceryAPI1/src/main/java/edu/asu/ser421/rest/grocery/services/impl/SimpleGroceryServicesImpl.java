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
	public List<GroceryItem> findAll() throws Exception {
		try {
			return new ArrayList<GroceryItem>(__groceryItems.values());
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public GroceryItem findOne(String id) throws Exception {
		try {
			return __groceryItems.get(id);
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public String create(GroceryItem item) throws Exception {
		try {
			// we are responsible for creating IDs, so replace whatever came in
			// Note there are other ways to interpret this, but this is most RESTful
			String newId = __getNewId();
			if (newId != null) {
				item.setId(newId);
			} else {
				// uh-oh as a null it means we could not find a new ID
				throw new Exception("Unable to generate new ID for GroceryItem");
			}
			__groceryItems.put(item.getId(), item);
			return item.getId();  // we don't need to do this but we will
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public boolean update(GroceryItem item) throws Exception {
		try {
			boolean rval = __groceryItems.containsKey(item.getId());
			// we are updating regardless of what rval is, we are writing anyway
			// but it does affect the return status code and the id
			if (!rval) {
				// this is a create so we have to find a new ID
				String newId = __getNewId();
				if (newId != null) {
					item.setId(newId);
				} else {
					// uh-oh as a null it means we could not find a new ID
					throw new Exception("Unable to generate new ID for GroceryItem");
				}
			}
			__groceryItems.put(item.getId(), item);
			// Unlike create, we have multiple outcomes so we need to return something that says that
			return rval; // true if update, false if create
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public boolean delete(String id) throws Exception {
		try {
			boolean rval = __groceryItems.containsKey(id);
			if (rval) {
				__groceryItems.remove(id);
			}
			return rval; // true if deleted, false if not there
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public List<GroceryItem> findByCategory(GroceryType category) {
		return __groceryItems.values().stream().filter(x -> x.getGroceryType().equals(category)).collect(Collectors.toList());
	}

	// function to generate a random string of length 3
	// adapted from: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
	private String __getNewId() {
		boolean rval = false;
		//chose a Character random from this String
		String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(3);

		int numRetries = 0;
		while (!rval && numRetries < 10) {
			for (int i = 0; i < 3; i++) {
				//generate a random number between
				//0 to AlphaNumericString variable length
				int index = (int)(AlphaString.length() * Math.random());
				//add Character one by one in end of sb
				sb.append(AlphaString.charAt(index));
			}
			rval = !__groceryItems.containsKey(sb.toString());
		}
		// if we exit this loop we either found a new ID or we did not based on rval
		if (!rval) {  // if rval is false then we never found it
			return null;
		} else {
			return sb.toString();
		}
	}
}




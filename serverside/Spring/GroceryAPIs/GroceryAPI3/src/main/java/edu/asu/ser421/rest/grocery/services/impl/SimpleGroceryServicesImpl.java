package edu.asu.ser421.rest.grocery.services.impl;

import static edu.asu.ser421.rest.grocery.services.ProducerServices.getProducerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashMap;

import edu.asu.ser421.rest.grocery.exceptions.GroceryConflictException;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchGroceryItemException;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchGroceryProducerException;
import edu.asu.ser421.rest.grocery.exceptions.NoSuchProducerException;
import edu.asu.ser421.rest.grocery.model.GroceryItem;
import edu.asu.ser421.rest.grocery.model.GroceryItem.GroceryType;
import edu.asu.ser421.rest.grocery.model.Producer;
import edu.asu.ser421.rest.grocery.services.GroceryServices;
import edu.asu.ser421.rest.grocery.services.ProducerServices;

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
		try {
			__groceryItems = new HashMap<String, GroceryItem>() {{   // a hack to demo relationships
					put("MLK", new GroceryItem("MLK", "milk", GroceryType.DAIRY, 3.99f, getProducerService().findAll()));  
					put("SWC", new GroceryItem("SWC", "swiss cheese", GroceryType.DAIRY, 4.49f, null));
					put("YOG", new GroceryItem("YOG", "yogurt", GroceryType.DAIRY, 0.99f, null));
					put("WHB", new GroceryItem("WHB", "wheat bread", GroceryType.BREADS, 2.79f, null));
					put("GRB", new GroceryItem("GRB", "garlic bread", GroceryType.BREADS, 1.99f, null));
					put("APL", new GroceryItem("APL", "apples", GroceryType.PRODUCE, 0.69f, null));
					put("BRC", new GroceryItem("BRC", "broccoli", GroceryType.PRODUCE, 1.19f, null));
					put("PAS", new GroceryItem("PAS", "pastrami", GroceryType.DELI, 8.99f, null));
					put("HAM", new GroceryItem("HAM", "ham", GroceryType.PRODUCE, 5.69f, null));
			}};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public GroceryItem findOne(String id) throws NoSuchGroceryItemException, Exception {
		try {
			GroceryItem gItem = __groceryItems.get(id);
			if (gItem == null) {
				throw new NoSuchGroceryItemException(id);
			}
			return gItem;
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
	public boolean delete(String id) throws NoSuchGroceryItemException, Exception {
		try {
			boolean rval = __groceryItems.containsKey(id);
			if (rval) {
				__groceryItems.remove(id);
			} else {
				throw new NoSuchGroceryItemException(id);
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

	@Override
	public List<Producer> findAllGroceryProducers(String groceryId) throws NoSuchGroceryItemException, Exception {
		try {
			GroceryItem gItem = findOne(groceryId);
			if (gItem != null) {
				return gItem.getProducers();
			} else {
				throw new NoSuchGroceryItemException(groceryId);
			}
		} catch(Throwable t) {
			throw new Exception(t);
		}
	}
	
	@Override
	public Producer findOneGroceryProducer(String groceryId, String producerAbbrev) 
			throws NoSuchGroceryItemException, NoSuchGroceryProducerException, Exception  {
		
		GroceryItem gItem = this.findOne(groceryId);
		if (gItem == null) {
			throw new NoSuchGroceryItemException(groceryId);
		}
		// OK now we have a valid grocery id, check for the Producer
		// this whole block should really be synchronized - why?
		synchronized(gItem) {
			List<Producer> producers = gItem.getProducers();
			if (producers == null) {
				throw new NoSuchGroceryProducerException(groceryId, producerAbbrev);
			}
			// does the relationship already exist? If it does not we add and return true
			Optional<Producer> producer = producers.stream().filter(p -> p.getAbbreviation().equals(producerAbbrev)).findFirst();
			if (producer.isPresent()) {
				return producer.get();
			} else {
				throw new NoSuchGroceryProducerException(groceryId, producerAbbrev);
			}
		}
	}

	@Override
	public boolean addGroceryProducer(String groceryId, String producerAbbrev) 
			throws NoSuchGroceryItemException, NoSuchProducerException, Exception {
		boolean rval = false;  // by default we assume the relationship exists
		GroceryItem gItem = this.findOne(groceryId);
		if (gItem == null) {
			throw new NoSuchGroceryItemException(groceryId);
		}
		Producer producer = ProducerServices.getProducerService().findOne(producerAbbrev);
		if (producer == null) {
			throw new NoSuchProducerException(producerAbbrev);
		}
		// OK now we have a valid grocery id and a valid producer id
		// this whole block should really be synchronized - why?
		synchronized(gItem) {
			List<Producer> producers = gItem.getProducers();
			if (producers == null) {
				producers = new ArrayList<Producer>();
				gItem.setProducers(producers);
			} 
			// does the relationship already exist? If it does not we add and return true
			if (rval = (!producers.stream().filter(p -> p.getAbbreviation().equals(producerAbbrev)).findFirst().isPresent())) {
				// false. no relationship exists, create it
				producers.add(producer);
			}
		}
		return rval;
	}

	@Override
	public boolean deleteGroceryProducer(String groceryId, String producerAbbrev)
			throws NoSuchGroceryItemException, NoSuchProducerException, GroceryConflictException, Exception {
		boolean rval = false;  // by default we assume the relationship exists
		GroceryItem gItem = this.findOne(groceryId);
		if (gItem == null) {
			throw new NoSuchGroceryItemException(groceryId);
		}
		Producer producer = ProducerServices.getProducerService().findOne(producerAbbrev);
		if (producer == null) {
			throw new NoSuchProducerException(producerAbbrev);
		}
		// OK now we have a valid grocery id and a valid producer id
		// this whole block should really be synchronized - why?
		synchronized(gItem) {
			List<Producer> producers = gItem.getProducers();
			if (producers == null) {  // easy exit case
				return false;
			}
			// check the producers to see if it is there. This checks id field, could also implement equals
			if (rval = (producers.stream().filter(p -> p.getAbbreviation().equals(producerAbbrev)).findFirst().isPresent())) {
				// true. relationship exists, remove it
				producers.remove(producer);
			}
		}
		return rval;
	}
}




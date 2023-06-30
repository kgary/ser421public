package edu.asu.ser421.rest.grocery.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.HashMap;

import edu.asu.ser421.rest.grocery.model.Producer;
import edu.asu.ser421.rest.grocery.services.ProducerServices;

public class SimpleProducerServicesImpl implements ProducerServices {

	// Implementing as a Singleton. Note that this implementation needs to be thread-safe
	private static ProducerServices __theService = null;
	public static ProducerServices getGroceriesService() {
		if (__theService == null) {
			__theService = new SimpleProducerServicesImpl();
		}
		return __theService;
	}
	private Map<String, Producer> __producers = null;
	
	@SuppressWarnings("serial")
	private SimpleProducerServicesImpl() {
		//let's create some mock data
		__producers = new HashMap<String, Producer>() {{
				put("LOLK", new Producer("LOLK", "Land O Lakes", "1 Milky Way"));
				put("LUCR", new Producer("LUCR", "Lucerne", "100 Over the Moon Road"));
				put("YOPL", new Producer("YOPL", "Yoplait", "333 Yogurt is disgusting street"));
				put("RUBN", new Producer("RUBN", "Ruben's Bread", "279 Wonderborad Avenue"));
				put("FRUT", new Producer("FRUT", "Frumpy Fruit", "12 I Prefer Cheezits Way"));
				put("BRHD", new Producer("BRHD", "Boar's Head", "33 Urboring Lane"));
		}};
	}

	@Override
	public List<Producer> findAll() throws Exception {
		try {
			return new ArrayList<Producer>(__producers.values());
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public Producer findOne(String abb) throws Exception {
		try {
			return __producers.get(abb);
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public String create(Producer p) throws Exception {
		try {
			// we are responsible for creating IDs, so replace whatever came in
			// Note there are other ways to interpret this, but this is most RESTful
			String newAbbreviation = __getNewAbbreviation();
			if (newAbbreviation != null) {
				p.setAbbreviation(newAbbreviation);
			} else {
				// uh-oh as a null it means we could not find a new ID
				throw new Exception("Unable to generate new ID for Producer");
			}
			__producers.put(p.getAbbreviation(), p);
			return p.getAbbreviation();  // we don't need to do this but we will
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	@Override
	public boolean update(Producer p) throws Exception {
		try {
			boolean rval = __producers.containsKey(p.getAbbreviation());
			// we are updating regardless of what rval is, we are writing anyway
			// but it does affect the return status code and the id
			if (!rval) {
				String newAbbreviation = __getNewAbbreviation();
				if (newAbbreviation != null) {
					p.setAbbreviation(newAbbreviation);
				} else {
					// uh-oh as a null it means we could not find a new ID
					throw new Exception("Unable to generate new ID for Producer");
				}
				__producers.put(p.getAbbreviation(), p);
			}
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
			boolean rval = __producers.containsKey(id);
			if (rval) {
				__producers.remove(id);
			}
			return rval; // true if deleted, false if not there
		} catch (Throwable t) {
			// why do we handle unchecked exceptions? In writing the most robust code we
			// should always trap anything that could make it back through our response pipeline
			throw new Exception(t);
		}
	}

	// function to generate a random string of length 3
	// adapted from: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
	private String __getNewAbbreviation() {
		boolean rval = false;
		//chose a Character random from this String
		String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(4);

		int numRetries = 0;
		while (!rval && numRetries < 10) {
			for (int i = 0; i < 4; i++) {
				//generate a random number between
				//0 to AlphaNumericString variable length
				int index = (int)(AlphaString.length() * Math.random());
				//add Character one by one in end of sb
				sb.append(AlphaString.charAt(index));
			}
			rval = !__producers.containsKey(sb.toString());
		}
		// if we exit this loop we either found a new ID or we did not based on rval
		if (!rval) {  // if rval is false then we never found it
			return null;
		} else {
			return sb.toString();
		}
	}

	@Override
	public boolean changeAddress(String abbrev, String newAddress) throws Exception {
		Producer p = findOne(abbrev);
		if (p == null) {
			return false;   // no such Producer
		}
		p.setAddress(newAddress);
		return true;
	}
}




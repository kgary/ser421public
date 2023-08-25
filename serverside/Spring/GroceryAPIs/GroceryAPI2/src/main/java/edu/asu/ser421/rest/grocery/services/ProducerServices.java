package edu.asu.ser421.rest.grocery.services;

import static java.lang.Class.forName;

import java.lang.reflect.Method;
import java.util.List;

import edu.asu.ser421.rest.grocery.model.Producer;

public interface ProducerServices {
	public List<Producer> findAll() throws Exception;
	public Producer findOne(String abbrev) throws Exception;
	public String create(Producer p) throws Exception;		// returns the new Id
	public boolean update(Producer p) throws Exception;   	// true if update, false if create
	public boolean delete(String abbrev) throws Exception;			// true if id there, false if not
	// Let's do a PATCH for fun
	public boolean changeAddress(String abbrev, String newAddress) throws Exception;  // true if id found and patch done, false if not
	
	// normally we would implement a configurable logic to decide our service implementation
	public static ProducerServices getProducerService() {
		try {
			String __theServiceImpl = "edu.asu.ser421.rest.grocery.services.impl.SimpleProducerServicesImpl";
			System.out.println("The service impl is " + __theServiceImpl);
			Class<ProducerServices> implClass = (Class<ProducerServices>) forName(__theServiceImpl);
			Method m = implClass.getMethod("getGroceriesService");
			return (ProducerServices) m.invoke(null); // invoking a static method
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
}

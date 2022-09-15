package edu.asupoly.ser421.booktown.services;

import java.util.Properties;

import edu.asupoly.ser421.booktown.services.impl.SimpleBooktownServiceImpl;

public class BooktownServiceFactory {
    private BooktownServiceFactory() {}

    public static BooktownService getInstance() {
	// should really read from a property here
	if (__bookservice == null) {
	    __bookservice = new SimpleBooktownServiceImpl();
	}
	return __bookservice;
    }

    private static BooktownService __bookservice;
    
	// This class is going to look for a file named booktown.properties in the classpath
	// to get its initial settings
	static {
		try {
			Properties dbProperties = new Properties();
			Class<?> initClass = null;
			dbProperties.load(BooktownServiceFactory.class.getClassLoader().getResourceAsStream("booktown.properties"));
			String serviceImpl = dbProperties.getProperty("serviceImpl");
			if (serviceImpl != null) {
				initClass = Class.forName(serviceImpl);
			} else {
				initClass = Class.forName("edu.asupoly.ser421.booktown.services.impl.SimpleBooktownService");
			}
			__bookservice = (BooktownService)initClass.getDeclaredConstructor().newInstance();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}
    
}

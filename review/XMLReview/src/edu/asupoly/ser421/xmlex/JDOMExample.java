package edu.asupoly.ser421.xmlex;

import java.io.File;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 * Basic JDOM Example
 * Outputs any local XML file specified on the command line
 * Example usage:
 * java JDOMExample person.xml.xml
 */

public class JDOMExample {
	public static void main (String[] args) {
		System.out.println ("JDOMExample");
		System.out.println ("Using file:  "+args[0]);
		try {
			//  Use SAXBuilder
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(args[0]));
			//  Use XMLOutputter
			XMLOutputter out = new XMLOutputter ();
			out.output (doc, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

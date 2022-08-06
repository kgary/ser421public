package	    edu.asupoly.ser421.xmlex;

import      javax.xml.parsers.SAXParserFactory;
import      javax.xml.parsers.SAXParser;

import	    java.io.File;
import      org.xml.sax.SAXParseException;
import	    org.xml.sax.helpers.DefaultHandler;
import	    org.xml.sax.XMLReader;
import	    org.xml.sax.Attributes;

public class SAXExample extends DefaultHandler {

	/**
	 *  This new importExamInfo reads the xml from the specified uri.
	 */
	private void parseDocument (String uri) {

		SAXParserFactory spf = null;
		SAXParser sp = null;

		// This is the new JAXP code
		try {
			spf = SAXParserFactory.newInstance();
			sp  = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			//xr.setFeature("http://xml.org/sax/features/validation", true);
			xr.setContentHandler(this);
			xr.setErrorHandler(this);

			// xr.setDTDHandler(this);
			// xr.setEntityResolver(this);
			sp.parse(new File(uri), this);
		} catch (Exception e) { e.printStackTrace(); };

	} // end createDocument

	// for Error Handler
	public void warning(SAXParseException spe) {
		spe.printStackTrace();
		System.out.println("Warning occurred " + spe);
	}
	public void error(SAXParseException spe) {
		spe.printStackTrace();
		System.out.println("Error occurred " + spe);
	}

	public void startDocument() {
		System.out.println("startDocument");
	}

	public void startElement(String uri, String localName, 
			String qName, Attributes attributes) {

		System.out.println("startElement");
		System.out.print("  "+localName);
		System.out.println("  "+qName);
	}
	public void endElement(String uri, String localName, String qName) {
		System.out.println("endElement");
		System.out.print("  "+localName);
		System.out.println("  "+qName);
	}

	public void endDocument() {
		System.out.println("endDocument");
	}

	public static void main (String args[]) {
		SAXExample s = new SAXExample();
		if (args.length != 1) {
			System.out.println("USAGE: java edu.asupoly.ser421.xmlex.SAXExample <file>");
		}
		else {
			s.parseDocument(args[0]);
		}
		System.exit(0);
	}
}

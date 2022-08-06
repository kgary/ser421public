package edu.asupoly.ser421.xmlex;

import javax.xml.parsers.*;
import java.io.*;

import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class DOMExample {

	public static void main(String args[]) throws Exception {

		InputSource source = new InputSource(new FileReader(args[0]));
		Document doc = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		//factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(source);

		Element root = doc.getDocumentElement();
		root.normalize();

		// DOM Navigation
		System.out.println("ROOT CHILDREN:");
		NodeList rootChildren = root.getChildNodes();
		for (int i=0; i < rootChildren.getLength(); i++) {
			Node x = rootChildren.item(i);
			System.out.println("Type:" + x.getNodeType() + "\t" + 
					"Name:" + x.getNodeName() + "\t" +
					"Value: *" + x.getNodeValue() + "*");
		}

		// Name is in form Last, First
		String name = root.getChildNodes().item(1)
				.getChildNodes().item(3)
				.getChildNodes().item(0).getNodeValue()
				+ ", " +
				root.getChildNodes().item(1)
				.getChildNodes().item(1)
				.getChildNodes().item(0).getNodeValue();
		System.out.println("Name (DOM): " + name);
	}
}

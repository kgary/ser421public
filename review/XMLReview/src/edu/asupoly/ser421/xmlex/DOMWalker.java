package edu.asupoly.ser421.xmlex;

import javax.xml.parsers.*;
import java.io.*;

import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DOMWalker {

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
		walkFromNode(root, 0);
	}

	static void walkFromNode(Node n, int depth) {
		NodeList rootChildren = n.getChildNodes();
		for (int i=0; i < rootChildren.getLength(); i++) {
			Node x = rootChildren.item(i);
			System.out.println("Type:" + x.getNodeType() + "\t" + 
					"Name:" + x.getNodeName() + "\t" +
					"Value: *" + x.getNodeValue() + "*");

			walkFromNode(x, depth+1);
		}
	}
}

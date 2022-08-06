/**
 * This example taken from http://viralpatel.net/blogs/java-xml-xpath-tutorial-parse-xml/
 * Author: Viral Patel
 * Academic use only, all rights belong to the author
 */

package edu.asupoly.ser421.xmlex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathExample {
	public static void main(String[] args) {

		try {
			FileInputStream file = new FileInputStream(new File(args[0]));
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			XPath xPath =  XPathFactory.newInstance().newXPath();

			String expression = args[1];
			System.out.println(expression);
			String output = xPath.compile(expression).evaluate(xmlDocument);
			System.out.println(output);
	
			// if it has any
			System.out.println("NODELIST LOOP");
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
			}
			// if it has any
			System.out.println("NODE ELEMENT LOOP");
			Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			if(null != node) {
				nodeList = node.getChildNodes();
				for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) {
					Node nod = nodeList.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE)
						System.out.println(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}      
	}
}

/**
 * SessionExampleUtils.java - utilities for a faux-session like behavior for simple servlet examples
 */

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * @author kgary
 *
 */
public final class SessionExampleUtils {
    private static Map<String, ArrayList<String>> sessions = new HashMap<String, ArrayList<String>>();
	
    private SessionExampleUtils() {
    }

    static String[] computeClientPost(String[] items, PrintWriter out, String newItem, String action) {
	String[] newItems = null;

	out.println("<HEAD><TITLE>Shopping Cart Action</TITLE></HEAD>");
	out.println("<BODY>");
	    
	if (action != null && !action.isEmpty()) {
	    if (action.trim().indexOf("Add") >= 0 && newItem != null && !newItem.isEmpty()) {
		if (items == null) {
		    newItems = new String[1];
		    newItems[0] = newItem;
		} else {
		    newItems = new String[items.length+1];
		    System.arraycopy(items, 0, newItems, 0, items.length);
		    newItems[newItems.length-1] = newItem;
		}
		out.println("<p>Added new item to shopping cart: " + newItem + "</p>");
	    } else if (action.trim().indexOf("Remove") >= 0 && newItem != null && !newItem.isEmpty()) {
		boolean found = false;
		int i = 0;
		for (; !found && items != null && i < items.length; i++) {
		    found = newItem.equals(items[i]);
		}
		if (found) {
		    i--;
		    newItems = new String[items.length-1];
		    System.arraycopy(items, 0, newItems, 0, i);
		    System.arraycopy(items, i+1, newItems, i, newItems.length-i);
		    out.println("Removed " + newItem + " from shopping cart</p>");
		} else {
		    out.println("<p>Could not find item to remove: " + newItem + "</p>");
		    newItems = items;
		}
	    } else if (action.trim().indexOf("Check") >= 0) {
		newItems = new String[0];
		out.println("<p>You are checked out!</p>");
	    } else {
		newItems = items;
		out.println("<p>No action taken</p>");
	    }
	} else {
	    newItems = items;
	    out.println("<p>No action taken!</p>");
	}
	return newItems;
    }
	
    static String[] getItemsFromCart(String sessionid) {
	System.out.println("CART SESSION ID: " + sessionid);
	ArrayList<String> stuff = getStuff(sessionid);
	if (stuff != null) {
	    System.out.println("Returning number of items = " + stuff.size());
	    return stuff.toArray(new String[stuff.size()]);
	} else {
	    return null;
	}
    }
    static void addItemToCart(String sessionid, String item) {
	ArrayList<String> stuff = getStuff(sessionid);
	System.out.println("ADD SESSION ID: " + sessionid);
	if (stuff == null) {
	    stuff = new ArrayList<String>();
	    sessions.put(sessionid, stuff);
	}
	System.out.println("Added to cart: " + item);
	stuff.add(item);
    }
    static void removeItemFromCart(String sessionid, String item) {
	ArrayList<String> stuff = getStuff(sessionid);
	if (stuff != null) {
	    stuff.remove(item);
	}
    }
    private static ArrayList<String> getStuff(String sessionid) {
	return sessions.get(sessionid);
    }

    public static void clear(String sessionid) {
	sessions.remove(sessionid);
    }

    static String generateSessionId() throws UnsupportedEncodingException {
	// add an entry to our stuff too

	String uid = new java.rmi.server.UID().toString(); // guaranteed unique
	uid = URLEncoder.encode(uid,"UTF-8"); // encode any special chars
	sessions.put(uid, new ArrayList<String>());
	return uid;
    }

    static void printForm(String requestURI, PrintWriter out, String sessionid, boolean isHidden) {
	// Ask if they want to add more items or check out.
	out.println("<FORM ACTION=\"" + requestURI + "\" METHOD=POST>");
	if (isHidden) {
	    out.println("<INPUT TYPE=\"hidden\" NAME=\"sessionid\" VALUE=\"" + sessionid + "\"/>");
	}
	out.println("Enter an item: <INPUT TYPE=\"TEXT\" NAME=\"item\" SIZE=\"20\">");
	out.println("<BR/>Would you like to<BR/>");
	out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Add Item \">");
	out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Remove Item \">");
	out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Check Out \">");
	out.println("</FORM>");
    }
	
    static void printPage(String requestURI, String sessionid, PrintWriter out, boolean isHidden) {
	out.println("<HEAD><TITLE>Current Shopping Cart Items</TITLE></HEAD>");
	out.println("<BODY>");

	// Cart items are associated with the session ID
	String[] items = SessionExampleUtils.getItemsFromCart(sessionid);

	// Print the current cart items.
	out.println("You currently have the following items in your cart:<BR>");
	if (items == null) {
	    out.println("<B>None</B>");
	} else {
	    out.println("<UL>");
	    for (int i = 0; i < items.length; i++) {
	        out.println("<LI>" + items[i]);
	    }
	    out.println("</UL>");
	}
	printForm(requestURI, out, sessionid, isHidden);
	out.println("</BODY></HTML>");
    }

    static void printClientForm(PrintWriter out, String[] items, String requestURI, boolean isHidden) {
	// Print the current cart items.
	out.println("You currently have the following items in your cart:<BR>");
	if (items == null) {
	    out.println("<B>None</B>");
	}
	else {
	    out.println("<UL>");
	    for (int i = 0; i < items.length; i++) {
		out.println("<LI>" + items[i] + "</LI>");
	    }
	    out.println("</UL>");
	}
		    
	// Ask if the user wants to add more items or check out.
	// Include the current items as hidden fields so they'll be passed on.
	out.println("<FORM ACTION=\"" + requestURI + "\" METHOD=POST>");
	if (items != null && isHidden) {
	    for (int i = 0; i < items.length; i++) {
		out.println("<INPUT TYPE=HIDDEN NAME=\"item\" VALUE=\"" + items[i] + "\">");
	    }
	    out.println("</UL>");
	}
	out.println("Enter an item: <INPUT TYPE=\"TEXT\" NAME=\"newitem\" SIZE=\"20\">");
	out.println("<BR/>Would you like to<BR/>");
	out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Add Item \">");
	out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Remove Item \">");
	out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Check Out \">");
	out.println("</FORM>");
    }
}

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
   Example taken from http://www.java2s.com/Code/Java/Servlets
   and then augmented to provide multipage behavior
*/
@SuppressWarnings("serial")
public class ShoppingCartViewerRewriteClient extends HttpServlet {

    private static final String URL_DELIMITER = ":";

    private static String[] processQueryString(HttpServletRequest req, String param, String delim) {
	String cItems = req.getParameter(param);
	if (cItems != null && !cItems.isEmpty()) {
	    return cItems.split(delim);
	}
	return null;
    }
	
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String[] items = processQueryString(req, "items", URL_DELIMITER);
	    
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	out.println("<HEAD><TITLE>Rewrite Client Shopping Cart POST</TITLE></HEAD>");
	out.println("<BODY>");
	    
	// need to convert back to a proper delimited URL param
	String requestURI = req.getRequestURI();
	String newItem = req.getParameter("newitem");
	String action = req.getParameter("action");

	String[] newItems = SessionExampleUtils.computeClientPost(items, out, newItem, action);
	if (newItems != null && newItems.length > 0) {
	    StringBuffer sb = new StringBuffer();
	    for (String s : newItems) {
		sb.append(s + URL_DELIMITER);
	    }
	    String s = sb.toString();
	    requestURI = res.encodeURL(requestURI + "?items=" + s.substring(0, s.length()-1));
	}
	SessionExampleUtils.printClientForm(out, newItems, requestURI, false);
	out.println("</BODY></HTML>");
    }
	
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();

	out.println("<HEAD><TITLE>Rewrite Shopping Cart GET</TITLE></HEAD>");
	out.println("<BODY>");

	// Cart items are passed in as the item parameter.
	String[] items = processQueryString(req, "items", URL_DELIMITER);
	String requestURI = req.getRequestURI();
	if (items != null && items.length > 0) {
	    requestURI = requestURI + "?items=" + req.getParameter("items");
	}

	SessionExampleUtils.printClientForm(out, items, requestURI, false);
	out.println("</BODY></HTML>");
    }
}

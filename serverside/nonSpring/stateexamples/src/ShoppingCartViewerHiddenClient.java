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
public class ShoppingCartViewerHiddenClient extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String[] items = req.getParameterValues("item");

	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	out.println("<HEAD><TITLE>Hidden Shopping Cart POST</TITLE></HEAD>");
	out.println("<BODY>");
	    
	// need to convert back to a proper delimited URL param
	String requestURI = req.getRequestURI();

	String newItem = req.getParameter("newitem");
	String action = req.getParameter("action");

	SessionExampleUtils.printClientForm(out, SessionExampleUtils.computeClientPost(items, out, newItem, action), 
					    requestURI, true);
	out.println("</BODY></HTML>");
    }
	
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();

	out.println("<HEAD><TITLE>Hidden Shopping Cart GET</TITLE></HEAD>");
	out.println("<BODY>");

	// Cart items are passed in as the item parameter.
	String[] items = req.getParameterValues("item");
	String requestURI = req.getRequestURI();

	SessionExampleUtils.printClientForm(out, items, requestURI, true);
	out.println("</BODY></HTML>");
    }
}

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;

/**
   New example to show contrast of built-in HttpSession with other formats
 */
@SuppressWarnings("serial")
public class ShoppingCartViewerSession extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);  // create a session if we do not have one
		List<String> items = (List<String>)session.getAttribute("cart");

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println("<HEAD><TITLE>Shopping Cart Action</TITLE></HEAD>");
		out.println("<BODY>");

		String action = req.getParameter("action");
		String item   = req.getParameter("item");
		if (action != null && !action.isEmpty()) {
			if (action.trim().indexOf("Add") >= 0 && item != null && !item.isEmpty()) {
				// check to see if we have items in our session
				if (items == null) {
					items = new ArrayList<String>();
					// we add the attribute to the session here only if items is new.
					// if it wasn't we'd know the items and session pre-existed and that
					// the session already has an object reference to this collection.
					session.setAttribute("cart", items);
				}
				items.add(item);
			} else if (action.trim().indexOf("Remove") >= 0 && item != null && !item.isEmpty()) {
				if (items != null) {
					items.remove(item);
				}
				// we do not need to put items back in the session because it is already there!
			} else if (action.trim().indexOf("Check") >= 0) {
				// yes for this example we can remove the attribute "cart":
				items.clear();
				session.removeAttribute("cart");
			}
		}
		__printPage(req.getRequestURI(), items, out);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		HttpSession session = req.getSession();
		List<String> items = (List<String>)session.getAttribute("cart");

		__printPage(req.getRequestURI(), items, out);
	}

	private void __printPage(String requestURI, List<String> items, PrintWriter out) {
		out.println("<HEAD><TITLE>Current Shopping Cart Items</TITLE></HEAD>");
		out.println("<BODY>");

		// Print the current cart items.
		out.println("You currently have the following items in your cart:<BR>");
		if (items == null || items.isEmpty()) {
			out.println("<B>None</B>");
		} else {
			out.println("<UL>");
			for (String item : items) {
				out.println("<LI>" + item + "</LI>");
			}
			out.println("</UL>");
		}

		out.println("<FORM ACTION=\"" + requestURI + "\" METHOD=POST>");
		out.println("Enter an item: <INPUT TYPE=\"TEXT\" NAME=\"item\" SIZE=\"20\">");
		out.println("<BR/>Would you like to<BR/>");
		out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Add Item \">");
		out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Remove Item \">");
		out.println("<INPUT TYPE=SUBMIT NAME=\"action\" VALUE=\" Check Out \">");
		out.println("</FORM>");

		out.println("</BODY></HTML>");
	}
}

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
Example taken from http://www.java2s.com/Code/Java/Servlets
and then augmented to provide multipage behavior
 */
@SuppressWarnings("serial")
public class ShoppingCartViewerCookieClient extends HttpServlet {

	private static final String COOKIE_DELIMITER = ":";

	private static Cookie processCookie(HttpServletRequest req, String cookie) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookie)) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<HEAD><TITLE>Cookie Client Shopping Cart POST</TITLE></HEAD>");
		out.println("<BODY>");

		String[] items = null; 
		Cookie c = processCookie(req, "items");
		if (c != null) {
			items = c.getValue().split(COOKIE_DELIMITER);
		} else {
			c = new Cookie("items", "");
		}
		String[] newItems = SessionExampleUtils.computeClientPost(items, out, req);

		String requestURI = req.getRequestURI();
		SessionExampleUtils.printClientForm(out, newItems, requestURI, false);
		// need to convert back to a proper delimited Cookie

		if (newItems != null && newItems.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (String s1 : newItems) {
				sb.append(s1 + COOKIE_DELIMITER);
			}
			String s = sb.toString();
			res.addCookie(new Cookie("items", s.substring(0, s.length()-1)));
		} else {
			// remove the Cookie
			c.setMaxAge(0);
			res.addCookie(c);
		}
		out.println("</BODY></HTML>");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println("<HEAD><TITLE>Current Shopping Cart Items</TITLE></HEAD>");
		out.println("<BODY>");

		// Cart items are passed in as the item parameter.
		Cookie c = processCookie(req, "items");
		String[] items = null; 
		if (c  != null) {
			items = c.getValue().split(COOKIE_DELIMITER);
		} else {
			c = new Cookie("items", "");
		}
		String requestURI = req.getRequestURI();

		SessionExampleUtils.printClientForm(out, items, requestURI, false);
		res.addCookie(c);
		out.println("</BODY></HTML>");
	}
}
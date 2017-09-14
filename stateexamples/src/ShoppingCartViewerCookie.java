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
public class ShoppingCartViewerCookie extends HttpServlet {

	private static String processCookie(HttpServletRequest req) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	      for (int i = 0; i < cookies.length; i++) {
	        if (cookies[i].getName().equals("sessionid")) {
	          return cookies[i].getValue();
	        }
	      }
	    }
	    return null;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String sessionid = processCookie(req);
		
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	    
		if (sessionid == null) {
			out.println("Could not find your sessionid in cookie!");
		} else {
			String action = req.getParameter("action");
			String item   = req.getParameter("item");
			if (action != null && !action.isEmpty()) {
				if (action.trim().indexOf("Add") >= 0 && item != null && !item.isEmpty()) {
					SessionExampleUtils.addItemToCart(sessionid, item);
					out.println("Successfully added " + item + " to cart");
				} else if (action.trim().indexOf("Remove") >= 0 && item != null && !item.isEmpty()) {
					SessionExampleUtils.removeItemFromCart(sessionid, item);
					out.println("Successfully removed " + item + " from cart");
				} else if (action.trim().indexOf("Check") >= 0) {
					SessionExampleUtils.clear(sessionid);
					out.println("You are checked out! Thanks for shopping!");
				} else {
					out.println("Improperly formed shopping cart request");
				}
			} else {
				out.println("Missing or improperly specified action");
			}
		}
	}
	
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String sessionid = processCookie(req);
    System.out.println("Cookie id is " + sessionid);
    
    // If the session ID wasn't sent, generate one.
    // Then be sure to send it to the client with the response.
    if (sessionid == null) {
      sessionid = SessionExampleUtils.generateSessionId();
      Cookie c = new Cookie("sessionid", sessionid);
      res.addCookie(c);
    }

    String requestURI = req.getRequestURI();
    SessionExampleUtils.printPage(requestURI, sessionid, out, false);
  }
}
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
public class ShoppingCartViewerHidden extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String sessionid = req.getParameter("sessionid");
		
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();

	    out.println("<HEAD><TITLE>Shopping Cart Action</TITLE></HEAD>");
	    out.println("<BODY>");
	    
		if (sessionid != null) {
			String action = req.getParameter("action");
			String item   = req.getParameter("item");
			if (action != null && !action.isEmpty()) {
				if (action.trim().indexOf("Add") >= 0 && item != null && !item.isEmpty()) {
					SessionExampleUtils.addItemToCart(sessionid, item);
				} else if (action.trim().indexOf("Remove") >= 0 && item != null && !item.isEmpty()) {
					SessionExampleUtils.removeItemFromCart(sessionid, item);
				} else if (action.trim().indexOf("Check") >= 0) {
					SessionExampleUtils.clear(sessionid);
				}
			}
		}
	    String requestURI = req.getRequestURI();
	    SessionExampleUtils.printPage(requestURI, sessionid, out, true);
	}
	
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String sessionid = req.getParameter("sessionid");
    
    // If the session ID wasn't sent, generate one.
    // Then be sure to send it to the client with the response.
    if (sessionid == null) {
      sessionid = SessionExampleUtils.generateSessionId();
    }

    String requestURI = req.getRequestURI();
    SessionExampleUtils.printPage(requestURI, sessionid, out, true);
  }
}
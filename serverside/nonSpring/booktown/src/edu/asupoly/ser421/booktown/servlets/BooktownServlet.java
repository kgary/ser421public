package edu.asupoly.ser421.booktown.servlets;

// If using < Tomcat10 these need to be javax.
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.util.List;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.IOException;

import edu.asupoly.ser421.booktown.model.Author;
import edu.asupoly.ser421.booktown.services.BooktownService;
import edu.asupoly.ser421.booktown.services.BooktownServiceFactory;

/**
 * This is an example of a Model 1 servlet. It contains Controller and View functionality
 * while using a service to access the Model.
 * @author kgary
 *
 */
@SuppressWarnings("serial")
public class BooktownServlet extends HttpServlet
{
	// init method should get the factory

	public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException

	{
		// some generic setup - our content type and output stream
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		// We'll put all our page contents in this buffer
		StringBuffer pageBuffer = new StringBuffer();
		pageBuffer.append("<html>\n<head>\n<title>Booktown Servlet</title>\n</head>\n<body>\n<H2>Authors:</H2>\n");

		BooktownService bookstore = null;
		// Make sure the service is available
		try {
			bookstore = BooktownServiceFactory.getInstance();
			if (bookstore == null) {
				// serious problem, perhaps a config error. For now punt.
				out.println("Servlet execution error: The bookstore is closed.");
				return;
			}
		}
		catch (Throwable t) {
			// you at least want to log these in a generic logger somewhere
			// but for now we'll dump to the screen...
			pageBuffer.append("Error while trying to retrieve authors!!!\n<br/>\n");
			pageBuffer.append("<pre>\n");
			pageBuffer.append(t.getMessage());
			pageBuffer.append("\n</pre>");
		}

		// Get the user "command"
		String action = req.getParameter("action");
		int authorId = -1;
		if (action != null && action.equalsIgnoreCase("delete")) {
			authorId = Integer.parseInt(req.getParameter("authorid"));
			if (bookstore.deleteAuthor(authorId)) {
				pageBuffer.append("Author deleted: " + authorId + "<br/><br/>\n");
			} else {
				pageBuffer.append("Could not delete author with id " + authorId + "<br/><br/>\n");
			}
		} else if (action != null && action.equalsIgnoreCase("create")) {
			String lname = req.getParameter("lastname");
			String fname = req.getParameter("firstname");
			if (lname != null && fname != null && lname.length() > 0 && fname.length() > 0) {
				authorId = bookstore.createAuthor(lname, fname);
				if (authorId > 0) {
					pageBuffer.append("Created new author<br/><br/>\n");
				} else {
					pageBuffer.append("ERROR: Unable to create an author!<br/><br/>\n");
				}
			} else {
				pageBuffer.append("Cannot create an author with an empty first or last name<br/><br/>\n");
			}
		}
		
		// No matter what the command was, we display the list of authors and a create form
		// Call a service to get the list of authors
		List<Author> authorsList = bookstore.getAuthors();

		if (authorsList == null || authorsList.size() == 0) {
			pageBuffer.append("No authors found!!!<br/>\n");
		}
		else {
			// finally, we have some authors
			pageBuffer.append("\n<table>\n<thead><tr><th>ID</th><th>Last Name</th><th>First Name</th></tr></thead>\n");
			Author nextAuthor = null;
			Iterator<Author> authorIter = authorsList.iterator();
			while (authorIter.hasNext()) {
				nextAuthor = (Author)authorIter.next();
				pageBuffer.append("\n<tr><td>");
				pageBuffer.append(nextAuthor.getAuthorID());
				pageBuffer.append("</td><td>");
				pageBuffer.append(nextAuthor.getLastName());
				pageBuffer.append("</td><td>");
				pageBuffer.append(nextAuthor.getFirstName());
				pageBuffer.append("</td><td><a href=\"./booktown?action=delete&authorid="+nextAuthor.getAuthorID()+"\">delete</a></td></tr>\n");
			}
			pageBuffer.append("</table>\n<br/>\n");
		}
		// Now display a form for create an author
		pageBuffer.append("<p><form action=\"./booktown\" method=\"get\">\n");
		pageBuffer.append("<input type=\"hidden\" name=\"action\" value=\"create\"/>\n");
        pageBuffer.append("Last name: <input type=\"text\" size=\"13\" name=\"lastname\"/><br/>");
        pageBuffer.append("First name: <input type=\"text\" size=\"12\" name=\"firstname\"/><br/>");  
        pageBuffer.append("<button type=\"submit\">Create author</button><br/></form><br/>");

		pageBuffer.append("</body></html>");
		out.println(pageBuffer.toString());
	}
}

package edu.asupoly.ser422.phone;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

@SuppressWarnings("serial")
public class PhoneServlet extends HttpServlet {
	private static String _filename = null;
	public void init(ServletConfig config) throws ServletException {
		// if you forget this your getServletContext() will get a NPE! 
		super.init(config);
		_filename = config.getInitParameter("phonebook");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param phonebook with value " + _filename);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException	{

		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<HTML><HEAD><TITLE>Phone List</TITLE></HEAD><BODY>");

		String action = req.getParameter("action");
		if (action == null || action.length() == 0) {
			out.println("No Action provided");
			out.println("</BODY></HTML>"); 
			return;
		}
		// now get the phonebook file as an input stream
		ServletContext sc = getServletContext();
		InputStream is = sc.getResourceAsStream(_filename);

		PhoneBook pbook = new PhoneBook(is);

		try {
			if (action.equalsIgnoreCase("List"))	{
				String[] entries = pbook.listEntries();
				for (int i = 0; i < entries.length; i++)
					out.println("<b>" + i + ":</b> " + entries[i] + "<br>");
			}
		}
		catch (Exception exc)
		{
			out.println("<p>Java exception satisfying request</p>");
			exc.printStackTrace();
		}
		out.println("</BODY></HTML>");
	}
}

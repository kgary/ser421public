package edu.asupoly.ser422.phone;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

@SuppressWarnings("serial")
public class BadPhoneServlet extends HttpServlet {
	private PhoneBook pbook = null;
	
	public void init(ServletConfig config) throws ServletException {
		// if you forget this your getServletContext() will get a NPE! 
		super.init(config);
		String filename = config.getInitParameter("phonebook");
		if (filename == null || filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param phonebook with value " + filename);
		// now get the phonebook file as an input stream
		// here we use an alternative method that gets it relative to the CLASSPATH
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
		try {
			pbook = new PhoneBook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		try {
			synchronized(pbook) {
				if (action.equalsIgnoreCase("List"))	{
					listEntries(out);
				} else if (action.equalsIgnoreCase("Sleep")) {

					Thread.sleep(5000L);
					out.println("<p>DONE SLEEPING!!!</p>");
				}
			}
			if (action.equalsIgnoreCase("Edit")) {
				String num = req.getParameter("phone");
				String fname = req.getParameter("firstname"); 
				String lname = req.getParameter("lastname");
				pbook.editEntry(num, fname, lname);
				out.println("<p>Done editing entry with " + num + ", " + fname + ", " + lname + "</p>");
				listEntries(out);
			}
		}
		catch (Exception exc)
		{
			out.println("<p>Java exception satisfying request</p>");
			exc.printStackTrace();
		}
		out.println("</BODY></HTML>");
	}
	
	private void listEntries(PrintWriter out) {
		String[] entries = pbook.listEntries();
		for (int i = 0; i < entries.length; i++)
			out.println("<b>" + i + ":</b> " + entries[i] + "<br>");
	}
}

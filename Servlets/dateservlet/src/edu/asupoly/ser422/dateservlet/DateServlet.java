package edu.asupoly.ser422.dateservlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

@SuppressWarnings("serial")
public class DateServlet extends javax.servlet.http.HttpServlet
{
    public void doGet (HttpServletRequest req, HttpServletResponse res)
    					throws ServletException, IOException

	{
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		out.println("<html>");
		out.println("<head><title>First Servlet</title></head>");
		out.println("<body><H2>Current time is</H2>");
		out.println( (new Date()).toString() );
		out.println("</body></html>");
	}
}

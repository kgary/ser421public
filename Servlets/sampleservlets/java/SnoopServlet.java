/*
 * @(#)SnoopServlet.java	1.20 97/11/17
 * 
 * Copyright (c) 1995-1997 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * CopyrightVersion 1.0
 */

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Snoop servlet. This servlet simply echos back the request line and
 * headers that were sent by the client, plus any HTTPS information
 * which is accessible.
 *
 * @version 	1.20
 * @author 	Various
 */
@SuppressWarnings("serial")
public class SnoopServlet extends HttpServlet {

    @SuppressWarnings({ "deprecation" })
	public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
    {
	PrintWriter	out;

	res.setContentType("text/html");
	out = res.getWriter ();

	out.println("<html>");
	out.println("<head><title>Snoop Servlet</title></head>");
	out.println("<body>");

	out.println("<h1>Requested URL:</h1>");
	out.println("<pre>");
	out.println (HttpUtils.getRequestURL (req).toString ());
	out.println("</pre>");

	Enumeration<String> enumer = getServletConfig().getInitParameterNames();
        if (enumer != null) {
            boolean first = true;
	    while (enumer.hasMoreElements()) {
		if (first) {
	            out.println("<h1>Init Parameters</h1>");
	            out.println("<pre>");
		    first = false;
                }
		String param = (String) enumer.nextElement();
                out.println(" "+param+": "+getInitParameter(param));
	    }
	    out.println("</pre>");
        }

	out.println("<h1>Request information:</h1>");
	out.println("<pre>");

	print(out, "Request method", req.getMethod());
	print(out, "Request URI", req.getRequestURI());
	print(out, "Request protocol", req.getProtocol());
	print(out, "Servlet path", req.getServletPath());
	print(out, "Path info", req.getPathInfo());
	print(out, "Path translated", req.getPathTranslated());
	print(out, "Query string", req.getQueryString());
	print(out, "Content length", req.getContentLength());
	print(out, "Content type", req.getContentType());
	print(out, "Server name", req.getServerName());
	print(out, "Server port", req.getServerPort());
	print(out, "Remote user", req.getRemoteUser());
	print(out, "Remote address", req.getRemoteAddr());
	print(out, "Remote host", req.getRemoteHost());
	print(out, "Authorization scheme", req.getAuthType());

	out.println("</pre>");
	
	Enumeration<String> e = req.getHeaderNames();
	if (e.hasMoreElements()) {
	    out.println("<h1>Request headers:</h1>");
	    out.println("<pre>");
	    while (e.hasMoreElements()) {
		String name = (String)e.nextElement();
		out.println(" " + name + ": " + req.getHeader(name));
	    }
	    out.println("</pre>");
	}

	e = req.getParameterNames();
	if (e.hasMoreElements()) {
	    out.println("<h1>Servlet parameters (Single Value style):</h1>");
	    out.println("<pre>");
	    while (e.hasMoreElements()) {
		String name = (String)e.nextElement();
		out.println(" " + name + " = " + req.getParameter(name));
	    }
	    out.println("</pre>");
	}

	e = req.getParameterNames();
	if (e.hasMoreElements()) {
	    out.println("<h1>Servlet parameters (Multiple Value style):</h1>");
	    out.println("<pre>");
	    while (e.hasMoreElements()) {
		String name = (String)e.nextElement();
		String vals[] = (String []) req.getParameterValues(name);
		if (vals != null) {
		    out.print("<b> " + name + " = </b>"); 
		    out.println(vals[0]);
		    for (int i = 1; i<vals.length; i++)
			out.println("           " + vals[i]);
		}
		out.println("<p>");
	    }
	    out.println("</pre>");
	}
	
	out.println("</body></html>");
    }

    private void print (PrintWriter out, String name, String value)
    {
	out.print(" " + name + ": ");
	out.println(value == null ? "&lt;none&gt;" : value);
    }

    private void print (PrintWriter out, String name, int value)
    {
	out.print(" " + name + ": ");
	if (value == -1) {
	    out.println("&lt;none&gt;");
	} else {
	    out.println(value);
	}
    }
}



/**
 * This program adapted from "Servlets and JSPs" book
 */
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditCookies extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
    throws IOException, ServletException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    Cookie[] cookies = request.getCookies();

    // Get parameters to add/edit a cookie.
    String cookieName = request.getParameter("name");
    String cookieValue = request.getParameter("value");
    if (cookieName != null && !cookieName.equals("")
        && cookieValue != null && !cookieValue.equals("")) {
      Cookie cookie = new Cookie(cookieName, cookieValue);
      response.addCookie(cookie);
      response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath() + "/cookies"));
    }

    // Delete a cookie if requested.
    String cookieToDelete = request.getParameter("deleteCookie");
    if (cookieToDelete != null && !cookieToDelete.equals("")) {
      for (int i=0;i<cookies.length;i++) {
        Cookie cookie = cookies[i];
        if(cookie.getName().equals(cookieToDelete)) {
          cookie.setMaxAge(0);
          response.addCookie(cookie);
          response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath() + "/cookies"));
        }
      }
    }

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Cookies sent by your client</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p>The following cookies were sent:</p>");
    if (cookies == null) {
      out.println("No cookies were sent!<br>");
    }
    else {
      // List the current cookies.
      for (int i=0; i<cookies.length ;i++) {
        Cookie cookie = cookies[i];
        out.println("<h3>Cookie #" +i + "</h3>");
        out.println("<form method=\"post\">");
        out.print("<b>Name</b>: <input name=\"name\" value=\"");
        out.println(cookie.getName()+"\"><br>");
        out.print("<b>Value</b>: <input name=\"value\"");
        out.println(" value=\""+cookie.getValue()+"\"><br>");
        out.print("<input type=\"submit\"");
        out.println(" value=\"Update Cookie\"><br><br>");
        out.println("</form>");
      }
    }
    // A form for creating a new cookie.
    out.println("<h3>Create a New Cookie</h3><br>");
    out.println("<form method=\"post\">");
    out.println("<b>Name</b>: <input name=\"name\"><br>");
    out.println("<b>Value</b>: <input name=\"value\"><br>");
    out.print("<input type=\"submit\"");
    out.println(" value=\"Add Cookie\"><br>");
    out.println("</form>");

    // A form to delete cookies.
    if (cookies!=null) {
      out.println("<h3>Delete a Cookie</h3>");
      out.println("<form method=\"post\">");
      out.println("<select name=\"deleteCookie\">");
      for (int i=0; i<cookies.length ;i++) {
        Cookie cookie = cookies[i];
        out.print("<option value=\""+cookie.getName()+"\">");
        out.println(cookie.getName()+"</option><br>");
      }
      out.println("</select>");
      out.print("<input type=\"submit\"");
      out.println("value=\"Delete Cookie\"><br>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }

  // Forward all POST requests to the doGet() method.
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
    throws IOException, ServletException {
      doGet(request, response);
  }
}

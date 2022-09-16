import java.io.*;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
public class Session2 extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
        throws jakarta.servlet.ServletException, java.io.IOException {

    	//String url = null;
        HttpSession sess = req.getSession();

        String name = (String) sess.getAttribute("name");
        int count = ((Integer) sess.getAttribute("count")).intValue();
System.out.println("Got " + name + " and " + count + " from session");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("name = " + name + "<br/>count = " + count +"<br/>");

        out.println("<a href=\"" +  res.encodeURL("Session3") + "\">Click to Next Servlet (Session3)</a>");

        /* The improper way */
        //out.println("<a href=\"Session3\">Click to Next Servlet (Session3)</a>");
        //out.println("</body></html>");

        sess.setAttribute("count", count+1);
        sess.setAttribute("id", "XX$$YY");
    }
}

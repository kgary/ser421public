import java.io.*;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
public class Session1 extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
        throws jakarta.servlet.ServletException, IOException {

        HttpSession sess = req.getSession(true);    // create the session
        sess.setAttribute("name", "Bob");
        sess.setAttribute("count", 123);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        String url = res.encodeURL("Session2");
        out.println("<a href=\"" + url + "\">Click to Next Servlet (Session2)</a>");
        out.println("</body></html>");
    }
}

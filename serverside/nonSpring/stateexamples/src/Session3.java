import java.io.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Session3 extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
        throws javax.servlet.ServletException, java.io.IOException {

        HttpSession sess = req.getSession();

        String name = (String) sess.getAttribute("name");
        int count = ((Integer) sess.getAttribute("count")).intValue();
        String id = (String) sess.getAttribute("id");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("name = " + name + "<br/>count = " + count);
        out.println("<br/>id = " + id);
        out.println("</body></html>");
        
        // just for the heck of it, invalidate the session.
        sess.invalidate();
    }
}

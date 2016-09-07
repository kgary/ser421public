import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.Base64Decoder;

@SuppressWarnings("serial")
public class AuthServlet extends HttpServlet {

    Hashtable<String,String> users = new Hashtable<String,String>();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Should really use a database!
        users.put("guest:guest", "allowed");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        String auth = req.getHeader("Authorization");

        if (!allowUser(auth)) {
            // Not allowed, so report he's unauthorized
            res.setHeader("WWW-Authenticate", "BASIC realm=\"users\"");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            out.println("You passed authentication!");
            out.println("Auth is : " + auth);
        }
    }

    protected boolean allowUser(String auth) throws IOException {
        if (auth == null) return false;  // no auth

        if (!auth.toUpperCase().startsWith("BASIC "))
            return false;  // we only do BASIC

        // Get encoded user and password, comes after "BASIC "
        String userpassEncoded = auth.substring(6);

        // Decode using any base 64 decoder (we use com.oreilly.servlet)
        String userpassDecoded = Base64Decoder.decode(userpassEncoded);

        // Is this user "allowed"
        if ("allowed".equals(users.get(userpassDecoded)))
            return true;
        else
            return false;
    }
}


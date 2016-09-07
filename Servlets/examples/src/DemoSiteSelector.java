/**
 * This program taken from an example off the Web at
 * http://www.java-tips.org/java-ee-tips/java-servlet/how-to-redirect-a-request-using-servlet.html
 * 
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DemoSiteSelector extends HttpServlet{
    
    Vector<String> sites = new Vector();
    Random random = new Random();
    
    public void init(ServletConfig config)throws ServletException{
        
        super.init(config);
        sites.addElement("http://www.google.com");
        sites.addElement("http://www.java-tips.org");
        sites.addElement("http://www.yahoo.com");
        sites.addElement("http://www.asu.edu");
        
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)
            throws ServletException,IOException{
        
        res.setContentType("text/plain");
        PrintWriter out= res.getWriter();
        int siteIndex = Math.abs(random.nextInt())%sites.size();
        String site = (String) sites.elementAt(siteIndex);
        res.setStatus(res.SC_MOVED_TEMPORARILY);
        res.setHeader("Location", site);
        
    }
}
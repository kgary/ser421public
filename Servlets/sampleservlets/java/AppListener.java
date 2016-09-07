import javax.servlet.*;

public class AppListener implements ServletContextListener, ServletContextAttributeListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.setAttribute("key", "FOO");
		context.log("Servlet context initialized");
	}

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.log ((String)context.getAttribute("key"));
		context.removeAttribute("key");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		ServletContext context = arg0.getServletContext();
		context.log ("Added attribute to ServletContext " + arg0.getName() + " with value " + arg0.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		ServletContext context = arg0.getServletContext();
		context.log ("Removed attribute from ServletContext " + arg0.getName() + " with value " + arg0.getValue());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		ServletContext context = arg0.getServletContext();
		context.log ("Replaced attribute from ServletContext " + arg0.getName() + " with value " + arg0.getValue());
	}
}

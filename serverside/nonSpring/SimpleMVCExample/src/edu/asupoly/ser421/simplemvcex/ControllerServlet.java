package edu.asupoly.ser421.simplemvcex;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
//import java.util.logging.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controller for MVC2 pattern
 */
public class ControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static Logger log = Logger.getLogger(ControllerServlet.class.getName());

	private static String errorPage = "redirect.jsp";
	private static Map<String, ActionHandler> handlers = new HashMap<String, ActionHandler>();
	private static Map<String, String> pageViews = new HashMap<String, String>();
	private String surveyFile = null;

	public void init(ServletConfig config) throws ServletException {
		// if you forget this your getServletContext() will get a NPE! 
		super.init(config);

		// We're going to store the survey answers file on the local filesystem in a property
		// configured in the webapp. That way when we redeploy we don't blow it away.
		surveyFile = config.getInitParameter("surveyanswers");
		if (surveyFile == null || surveyFile.length() == 0) {
			throw new ServletException();
		}

		// THIS PART CAME FROM YOUR T6MVC2 EXAMPLE (modified of course)
		// normally I might read the action mapping from a config file
		handlers.put("survey", new SurveyHandler()); // surveyerror, sessionerror, surveydone, survey
		handlers.put("match", new MatchHandler());   // match, matcherror
		handlers.put("preferences", new ActionHandler() {  // showpreferences
			public String handleIt(HttpServletRequest request, HttpServletResponse response) {
				return "showpreferences";  // there isn't anything for the BL to do here
			}
		});
		handlers.put("setpreferences", new ActionHandler() {  // set rendering preferences
			public String handleIt(HttpServletRequest request, HttpServletResponse response) {
				String pref = request.getParameter("preference");
				if (pref != null && !pref.isEmpty() && (pref.equals("horizontal") || pref.equals("vertical"))) {
					response.addCookie(new Cookie("mvcrendering", pref));
				}
				return "setpreferences";  // there isn't anything for the BL to do here
			}
		});

		// same goes for the page views too
		pageViews.put("surveydone", "/redirect.jsp");	
		pageViews.put("survey", "/survey.jsp");  	
		pageViews.put("match", "/match.jsp");
		pageViews.put("showpreferences", "/preferences.jsp");
		pageViews.put("setpreferences", "./setpreferences.jsp");
		pageViews.put("surveyerror", "/redirect.jsp");
		pageViews.put("matcherror", "/redirect.jsp");
		pageViews.put("sessionerror", "/redirect.jsp");
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();  // yes create a session if non-existent
		String forwardPage = errorPage;  // if we have an HTTP error

		Map<String, String[]> params = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = params.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String, String[]> pair = (Map.Entry<String,String[]>)it.next();
		    String[] values = pair.getValue();
		    System.out.print(pair.getKey() + " =");
		    for (String s : values) System.out.print(" " + s);
		    System.out.println("");
		}

		String action = request.getParameter("action");
		// username in the request supercedes username in an existing session

		String username = request.getParameter("username");
		System.out.println("U1: " + username);
		if (username != null) {
			session.setAttribute("username", username);
			System.out.println("U2 set name: " + username);
			response.addCookie(new Cookie("mvcname", username));
		} else { // if we still have no username we can't continue (see if below)
			username = (String)session.getAttribute("username");
			System.out.println("U3: " + username);
		}
		// we don't have username in the session and we don't have it in the request
		// we'll drop down to the error page

		//shove the survey answers filename into the request
		request.setAttribute("surveyanswersfile", surveyFile);

		// Forward to web application to page indicated by action
		ActionHandler handler = handlers.get(action);
		if (handler != null && username != null) {
			String result = handler.handleIt(request, response);
			if (result != null && result.length() > 0) {
				forwardPage = pageViews.get(result);
			}
			if (forwardPage == null || forwardPage.length() == 0) {
				forwardPage = errorPage;
			}
		}  else { // if the action or username are not setwe will forward to our error page automagically
		    System.out.println("Cannot get action handler for " + action + " or username " + username);
			request.setAttribute("mvcmessage", "Improper request, no action or username present");
		}

		request.getRequestDispatcher(response.encodeURL(forwardPage)).forward(request, response);
	}

	/**
	 * Handle forms
	 *
	 * @param request HTTP Request object
	 * @param response HTTP Response object
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// A more intelligent framework would do something more intelligent!

		// Follow our pattern - process request information first
		// every request has to have an action to be valid to this servlet
		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
			request.getRequestDispatcher("./error.jsp").forward(request, response);
		}
		// now we can do our action
		doAction(request, response);
	}

	/**
	 *
	 * @param request HTTP Request object
	 * @param response HTTP Response object
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// A more intelligent framework would do something more intelligent!
		doPost(request, response);
	}
}

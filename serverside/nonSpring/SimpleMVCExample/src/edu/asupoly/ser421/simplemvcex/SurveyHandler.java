package edu.asupoly.ser421.simplemvcex;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SurveyHandler implements ActionHandler{

	public String handleIt(HttpServletRequest req, HttpServletResponse response) {
		// we are entering a survey page. Gotta figger out where we are
		Survey survey = null;
		HttpSession session = req.getSession();  // session has to exist by now
		String saFile = (String)req.getAttribute("surveyanswersfile");
		
		if (session == null) {  // houston we have a problem. Should never happen
			req.setAttribute("mvcmessage", "There was no pre-existing session for the survey");
			return "sessionerror";
		}
		// first one has to be there, 2nd one doesn't
		String username = (String)session.getAttribute("username");
		String[] surveyAnswers = (String[])session.getAttribute("answers");
		// Should never fail
		try {
			survey = Survey.getSurvey();
		} catch (IOException exc) {
			req.setAttribute("mvcmessage", "Could not get the survey question for this page");
			return "surveyerror";
		}
		
		// if we don't have any answers yet, then create some
		if (surveyAnswers == null || surveyAnswers.length == 0) {
			surveyAnswers = SurveyAnswers.getSurveyAnswers(saFile).getAnswers(username);
			// if we still don't have them, create them
			if (surveyAnswers == null) {
				surveyAnswers = new String[survey.getNumPages()];
				// add them to the set of survey answers
				SurveyAnswers.getSurveyAnswers(saFile).setAnswers(username, surveyAnswers);
			}
			// and put them in the session
			session.setAttribute("answers", surveyAnswers);
		}
		
		// OK, now we can see if there were previous page answers to process in the request
		// This will be the page number of the page we just came from
		Integer pageNum = (Integer)session.getAttribute("pagenum");
		String pageAction = req.getParameter("submit");  // do this before checking pageNum
		if (pageNum == null) {
			pageAction = "next";
			pageNum = 0; // if there was no page then we must be entering the survey
		} else if (pageNum > 0 && pageNum <= survey.getNumPages()) {
			// check to see if there was a response on a page you were just on
			String surveyAnswer = req.getParameter("answer");
			if (surveyAnswer != null && !surveyAnswer.isEmpty()) {
				// shouldn't ever happen, but let's be defensive
				surveyAnswers[pageNum-1] = surveyAnswer;
			} 
		} else {  // should never happen
			req.setAttribute("mvcmessage", "Tried to access a page not on this survey!");
			session.invalidate();
			return "surveyerror"; 
		}

		// Note that if there wasn't an answer we don't worry about it
		
		// OK, worry about generating the next page
		// This has to be there and it has to be previous or next
		if (pageAction != null) {
			if (pageAction.equals("previous") && pageNum > 0) {
				pageNum--;
			}
			else if (pageAction.equals("next")) {
				if (pageNum >= survey.getNumPages()) {
					req.setAttribute("mvcmessage", "Thanks for completing the survey!!!");
					try {
						SurveyAnswers.getSurveyAnswers(saFile).saveAnswers();
					} catch (IOException e) {
						e.printStackTrace();
						req.setAttribute("mvcmessage", "Survey done, but a problem saving your results!");
					}
					session.invalidate();
					return "surveydone";
				} else {
					pageNum++;
				}
			}
		}
		// any other pageAction and we just stay where we are
		
		// if we dropped out it means we are still in the survey
		session.setAttribute("pagenum", pageNum);
		session.setAttribute("answers", surveyAnswers);
		return "survey";
	}
}

package edu.asupoly.ser421.simplemvcex;

import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MatchHandler implements ActionHandler {

	@Override
	public String handleIt(HttpServletRequest req, HttpServletResponse res) {
		String saFile = (String)req.getAttribute("surveyanswersfile");
		SurveyAnswers sa = SurveyAnswers.getSurveyAnswers(saFile);
		String username = req.getParameter("username");
		ArrayList<MatchBean> matches = new ArrayList<MatchBean>();
		
		if (sa != null && username != null) {
			String[] myAnswers = sa.getAnswers(username);
			Iterator<String> users = sa.getUsers();
			String[] nextAnswers = null;
			MatchBean matchBean = null;
			int matchCount = 0;

			for (String u = null; users.hasNext();) {
				u = users.next();
				if (!u.equalsIgnoreCase(username)) {
					nextAnswers = sa.getAnswers(u);
					matchCount = computeScore(myAnswers, nextAnswers);

					// biz rule - you have to have a match score > 0
					if (matchCount > 0) {
						matchBean = new MatchBean();
						matchBean.setUsername(u);
						matchBean.setMatchCount(matchCount);
						insertionSort(matches, matchBean);
					}
				}
			}
			req.setAttribute("matches", matches);
		}
		return "match";
	}
	
	private void insertionSort(ArrayList<MatchBean> matches, MatchBean bean) {
		boolean done = false;
		int index = 0;
		while (index < matches.size() && !done) {
			if (bean.getMatchCount() > matches.get(index).getMatchCount()) {
				done = true;
				matches.add(index, bean);
			}
			index++;
		}
		if (!done) { // add to end
			matches.add(bean);
		}
	}
	
	private int computeScore(String[] src, String[] target) {
		int rval = 0;
		if (src != null && target != null) {
			for (int i = 0; i < src.length && i < target.length; i++) {
				if (src[i] != null && target[i] != null && src[i].equals(target[i])) {
					rval++;
				}
			}
		}
		return rval;
	}
}

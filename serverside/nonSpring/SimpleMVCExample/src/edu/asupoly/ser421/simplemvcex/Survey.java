/**
 * 
 */
package edu.asupoly.ser421.simplemvcex;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.asupoly.ser421.simplemvcex.Survey;

/**
 * @author kgary
 *
 */
public final class Survey {
	private SurveyItem[] surveyItems;
	private static Survey _survey = null;
	private static final String SURVEYFILE = "survey.txt";
	
	public static Survey getSurvey() throws IOException {
		if (_survey == null) {
			_survey = new Survey(SURVEYFILE);
		}
		return _survey;
	}
	
	private Survey(String filename) throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		ArrayList<String> cs = new ArrayList<String>();
		ArrayList<SurveyItem> sis = new ArrayList<SurveyItem>();
		String nextChoice = null;
		String nextQuestion = br.readLine();
		while (nextQuestion != null)  {
			if (!nextQuestion.trim().isEmpty()) { // not a blank line, is a question
				// read the set of choices
				nextChoice = br.readLine();
				while (nextChoice != null && !nextChoice.isEmpty()) {
					cs.add(nextChoice);
					nextChoice = br.readLine();
				}
				sis.add(new SurveyItem(nextQuestion, cs.toArray(new String[cs.size()])));
				cs.clear();
			}
			nextQuestion = br.readLine();
		}
		br.close();
		surveyItems = sis.toArray(new SurveyItem[sis.size()]);
	}

	public SurveyItem getSurveyItem(int num) {
		if (num < 1 || num > surveyItems.length) {
			return null;
		} else {
			return surveyItems[num-1];
		}
	}
	public int getNumPages() {
		return surveyItems.length;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Number of Survey Items: " + getNumPages() + "\n");
		for (int i = 1; i <= getNumPages(); i++) {
			sb.append(getSurveyItem(i));
		}
		return sb.toString();
	}
}

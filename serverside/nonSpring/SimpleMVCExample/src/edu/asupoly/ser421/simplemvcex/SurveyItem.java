/**
 * 
 */
package edu.asupoly.ser421.simplemvcex;


/**
 * @author kgary
 *
 */
public final class SurveyItem {
	private String question;
	private String[] choices;
	
	public SurveyItem(String q, String[] cs) {
		question = q;
		choices = cs;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getChoices() {
		return choices;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nQuestion: " + getQuestion());
		for (int i = 0; choices != null && i < choices.length; i++) {
			sb.append("\n\tChoice: " + choices[i]);
		}
		return sb.toString();
	}
}

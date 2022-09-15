package edu.asupoly.ser421.simplemvcex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("serial")
public class SurveyAnswers implements java.io.Serializable {
	private String filename = null;
	
	private static SurveyAnswers _allSurveyAnswers = null;
	private Map<String, String[]> _allAnswers;
	
	public static SurveyAnswers getSurveyAnswers(String filename) {
		ObjectInputStream ois = null;
		if (_allSurveyAnswers == null) {
			File f = new File(filename);
			if (f.exists()) {
				try {		
					InputStream is = new FileInputStream(filename);
					ois = new ObjectInputStream(is);
					_allSurveyAnswers = (SurveyAnswers)ois.readObject();
				} catch (Exception exc) {
					// shouldn't happen...
					exc.printStackTrace();
					// kinda a weak recovery here...
					_allSurveyAnswers = new SurveyAnswers(filename);
				} finally {
					if (ois != null) {
						try { ois.close(); } catch (Throwable t) {}
					}
				}
			} else { // no such file exists
				_allSurveyAnswers = new SurveyAnswers(filename);
			}
		}
		return _allSurveyAnswers;
	}
	
	private SurveyAnswers(String filename) {
		this.filename = filename;
		// if we don't have some then let's create some. Note this doesn't saev anything
		_allAnswers = Collections.synchronizedMap(new HashMap<String, String[]>());
	}
	
	public String[] getAnswers(String user) {
		return _allAnswers.get(user);
	}
	public void setAnswers(String user, String[] answers) {
		_allAnswers.put(user, answers);
	}
	public Iterator<String> getUsers() {
		return _allAnswers.keySet().iterator();
	}
	public void saveAnswers() throws IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(_allSurveyAnswers);
			oos.close();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new IOException(t);
		}
	}
}


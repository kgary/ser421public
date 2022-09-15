package edu.asupoly.ser421.simplemvcex;

public class MatchBean {
	private String username;
	private int matchCount;
	
	public MatchBean() {
		username = "";
		matchCount = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
}

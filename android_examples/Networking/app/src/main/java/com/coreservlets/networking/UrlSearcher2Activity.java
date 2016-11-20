package com.coreservlets.networking;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

/** Checks to see if a given URL contains certain keywords.
 *  Displays the lines that match. Uses HttpClient instead
 *  of HttpURLConnection.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class UrlSearcher2Activity extends UrlSearcher1Activity {
    
    /** Finds the matching lines and displays them in a ScrollView */
    
    @Override
    protected void showResults(String urlString, String searchString) {
        try {
            String urlBody = HttpUtils.urlContent(urlString);
            String[] lines = urlBody.split("[\\n\\r]+");
            StringBuilder matches = new StringBuilder("");
            int lineNum = 0;
            int numMatches = 0;
            for (String line: lines) {
                lineNum++;
                if(line.contains(searchString)) {
                    numMatches++;
                    matches.append(makeMatch(line, lineNum));
                }
            }
            displayResults(matches, numMatches);
        } catch (ClientProtocolException cpe) {
            showError("Bad URL: " + urlString);
            cpe.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            showError("Error in connection: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        } 
    }
}
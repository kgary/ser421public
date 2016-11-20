package com.coreservlets.networking;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** Checks to see if a given URL contains certain keywords.
 *  Displays the lines that match.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class UrlSearcher1Activity extends Activity {
    protected EditText mUrlToSearch, mSearchString;
    protected TextView mUrlMessageResult;
    protected float mResultTextSize, mErrorTextSize;
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_searcher);
        mUrlToSearch = (EditText)findViewById(R.id.url_to_search);
        mSearchString = (EditText)findViewById(R.id.search_string);
        mUrlMessageResult = (TextView)findViewById(R.id.url_search_result);
        Resources resources = getResources();
        mResultTextSize = resources.getDimension(R.dimen.url_search_results_size);
        mErrorTextSize = resources.getDimension(R.dimen.url_search_error_size);
    }
    
    /** Reads the URL and search string entered by the user, 
     *  connects to the host and port contained in the URL, 
     *  and, if the connection is successful, displays 
     *  each line in the URL that contains the search string.
     */
    public void searchInUrl(View clickedButton) {
        String urlString = mUrlToSearch.getText().toString();
        String searchString = mSearchString.getText().toString();
        showResults(urlString, searchString);
    }
    
    /** Finds the matching lines and displays them in a ScrollView */
    
    protected void showResults(String urlString, String searchString) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            BufferedReader in = 
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            StringBuilder matches = new StringBuilder("");
            int lineNum = 0;
            int numMatches = 0;
            while ((line = in.readLine()) != null) {
                lineNum++;
                if(line.contains(searchString)) {
                    numMatches++;
                    matches.append(makeMatch(line, lineNum));
                }
            }
            displayResults(matches, numMatches);
        } catch (MalformedURLException mue) {
            showError("Bad URL: " + urlString);
            mue.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            showError("Error in connection: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    
    protected String makeMatch(String text, int lineNum) {
        return(String.format("LINE %s: %s%n", lineNum, text));
    }
    
    protected void displayResults(StringBuilder matches, int numMatches) {
        if (numMatches > 0) {
            showMatches(matches, numMatches);
        } else {
            showError("No matches");
        }
    }
    
    protected void showMatches(StringBuilder matches, int numMatches) {
        String introMessage = 
                String.format("FOUND %s MATCHES:%n%n", numMatches);
        matches.insert(0, introMessage);
        mUrlMessageResult.setTextSize(mResultTextSize);
        mUrlMessageResult.setText(matches); 
    }
    
    protected void showError(String text) {
        mUrlMessageResult.setTextSize(mErrorTextSize);
        mUrlMessageResult.setText("\n\n" + text);
    }
}
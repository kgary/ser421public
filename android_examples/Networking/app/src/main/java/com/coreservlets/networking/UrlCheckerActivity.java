package com.coreservlets.networking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/** Checks to see if a given URL exists or not, based on
 *  the server status line.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class UrlCheckerActivity extends Activity {
    private EditText mUrlToTest;
    private TextView mUrlMessageResult;
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_checker);
        mUrlToTest = (EditText)findViewById(R.id.url_to_test);
        mUrlMessageResult = (TextView)findViewById(R.id.url_validation_result);
    }
    
    /** Reads the URL entered by the user, connects to the host and port contained
     *  in the URL, and, if the connection is successful,
     *  prints out information from the server status line. 
     */
    public void checkUrl(View clickedButton) {
        String url = mUrlToTest.getText().toString();
        UrlParser parser = new UrlParser(url);
        String host = parser.getHost();
        int port = parser.getPort();
        String uri = parser.getUri();
        try {
            Socket socket = new Socket(host, port);
            PrintWriter out = SocketUtils.getWriter(socket);
            BufferedReader in = SocketUtils.getReader(socket);
            out.printf("HEAD %s HTTP/1.1\r\n", uri);
            out.printf("Host: %s\r\n", host);
            out.printf("Connection: close\r\n\r\n");
            String serverResult = in.readLine();
            String info = statusInfo(serverResult, in);
            mUrlMessageResult.setText(info);
            socket.close();
        } catch (UnknownHostException uhe) {
            mUrlMessageResult.setText("Unknown host: " + host);
            uhe.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            mUrlMessageResult.setText("IOException: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        }
    }
    
    private String statusInfo(String serverResult, BufferedReader in)
            throws IOException {
        StatusLineParser statusLine = new StatusLineParser(serverResult);
        String result;
        if (statusLine.isGood()) {
            result = String.format("Good URL: %s -- %s", 
                                   statusLine.getStatusCode(),
                                   statusLine.getMessage());
        } else if (statusLine.isForwarded()) {
            result = String.format("URL forwarded to %s", 
                                   location(in));
        } else {
            result = String.format("Bad URL: %s -- %s", 
                                   statusLine.getStatusCode(),
                                   statusLine.getMessage());
        }
        return(result);
    }
    
    private String location(BufferedReader in) throws IOException {
        String line;
        while((line = in.readLine()) != null) {
            if (line.toUpperCase().startsWith("LOCATION")) {
                String[] results = line.split("\\s+", 2);
                return(results[1]);
            }
        }
        return("(Unknown Location)");
    }
}
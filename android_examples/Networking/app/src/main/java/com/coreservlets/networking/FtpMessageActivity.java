package com.coreservlets.networking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/** Displays the welcome message of the specified FTP server.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class FtpMessageActivity extends Activity {
    private EditText mFtpHost;
    private TextView mFtpMessageResult;
    private static final int FTP_PORT = 21;
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ftp_message);
        mFtpHost = (EditText)findViewById(R.id.ftp_host);
        mFtpMessageResult = (TextView)findViewById(R.id.ftp_message_result);
    }
    
    /** Reads the host name entered by the user, tries to connect to
     *  port 21 on that host, and, if the connection is successful,
     *  prints out the welcome message. 
     */
    public void showMessage(View clickedButton) {
        String host = mFtpHost.getText().toString();
        try {
            Socket socket = new Socket(host, FTP_PORT);
            BufferedReader in = SocketUtils.getReader(socket);
            List<String> results = new ArrayList<String>();
            String line = in.readLine();
            results.add(line);
            if (line.startsWith("220-")) {
                while((line = in.readLine()) != null) {
                    results.add(line);
                    if ((line.equals("220") || line.startsWith("220 "))) {
                        break;
                    }
                }
            }
            String output = makeOutputString(results);
            mFtpMessageResult.setText(output);
            socket.close();
        } catch (UnknownHostException uhe) {
            mFtpMessageResult.setText("Unknown host: " + host);
            uhe.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            mFtpMessageResult.setText("IOException: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        }
    }
    
    private String makeOutputString(List<String> results) {
        StringBuilder output = new StringBuilder();
        for (String s: results) {
            output.append(s + "\n");
        }
        return(output.toString());
    }
}
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

/** Tries to verify whether or not a given email address is a "real" one.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class MailAddressVerifierActivity extends Activity {
    private EditText mAddressInput;
    private TextView mResultDisplay;
    private static final int SMTP_PORT = 25;
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_verifier);
        mAddressInput = (EditText)findViewById(R.id.address_input);
        mResultDisplay = (TextView)findViewById(R.id.address_result);
    }
    
    /** Reads the address entered by the user, and tries to verify it. */
    
    public void checkAddress(View clickedButton) {
        MailAddress address = new MailAddress(mAddressInput.getText().toString());
        String host = address.getHostname();
        try {
            System.out.println("[checkAddress] About to connect to SMTP server");
            Socket socket = new Socket(host, SMTP_PORT);
            PrintWriter out = SocketUtils.getWriter(socket);
            BufferedReader in = SocketUtils.getReader(socket);
            out.println("EXPN " + address.getUsername());
            System.out.println("[checkAddress] About to read result from SMTP server");
            String result = in.readLine();
            System.out.println("[checkAddress] Result from SMTP server: " + result);
            mResultDisplay.setText(result);
            socket.close();
        } catch (UnknownHostException uhe) {
            mResultDisplay.setText("Unknown host: " + host);
            uhe.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            mResultDisplay.setText("IOException: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        }
    }
}
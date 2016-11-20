package com.coreservlets.networking;

/** Takes a string of the form "user@host" and
 *  separates it into the "user" and "host" parts.
 *
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class MailAddress {
    private final String mUsername, mHostname;

    public MailAddress(String emailAddress) {
        String[] pieces = emailAddress.split("@");
        if (pieces.length != 2) {
            System.err.println("Illegal email address: " + emailAddress);
            mUsername = "unknown";
            mHostname = "example.com";
        } else {
            mUsername = pieces[0];
            mHostname = pieces[1];
        }
    }

    public String getUsername() {
        return (mUsername);
    }

    public String getHostname() {
        return (mHostname);
    }
}
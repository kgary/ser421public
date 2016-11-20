package com.coreservlets.networking;

import java.net.*;
import java.io.*;

/** Creates BufferedReaders and PrintWriters that are associated with a Socket, using
 *  abbreviated syntax.
 *
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class SocketUtils {
    /** Makes a BufferedReader to get incoming data. */

    public static BufferedReader getReader(Socket s) throws IOException {
        return (new BufferedReader(new InputStreamReader(s.getInputStream())));
    }

    /**
     * Makes a PrintWriter to send outgoing data. This PrintWriter will
     * automatically flush stream when println is called.
     */

    public static PrintWriter getWriter(Socket s) throws IOException {
        // Second argument of true means autoflush.
        return (new PrintWriter(s.getOutputStream(), true));
    }
}
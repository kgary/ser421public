package com.coreservlets.networking;

import java.util.*;

/** Parses the input to get a host, port, and uri (file). */

public class UrlParser {
    private String mHost, mUri, mProtocol;
    private int mPort = 80;

    public UrlParser(String url) {
        StringTokenizer tok = new StringTokenizer(url);
        mProtocol = tok.nextToken(":");
        if (isHttp()) {
            mHost = tok.nextToken(":/");
            try {
                mUri = tok.nextToken("");
                if (mUri.charAt(0) == ':') {
                    tok = new StringTokenizer(mUri);
                    mPort = Integer.parseInt(tok.nextToken(":/"));
                    mUri = tok.nextToken("");
                }
            } catch (NoSuchElementException nsee) {
                mUri = "/";
            }
        }
    }

    public String getHost() {
        return (mHost);
    }

    public int getPort() {
        return (mPort);
    }

    public String getUri() {
        return (mUri);
    }
    
    public String getProtocol() {
        return (mProtocol);
    }

    public boolean isHttp() {
        return("http".equals(mProtocol));
    }

    @Override
    public String toString() {
        return (String.format("port: %s, host: %s, uri: %s", mHost, mPort, mUri));
    }
}

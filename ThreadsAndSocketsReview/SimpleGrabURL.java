import java.net.*;
import java.io.*;

public class SimpleGrabURL {
    public static void main(String[] args) {
	if (args.length != 1) {
	    System.out.println("USAGE: java SimpleGrabURL <url>");
	    System.exit(0);
	}

	URLConnection conn = null;
	BufferedReader instream = null;
	try {
	    URL url = new URL(args[0]);
	    conn = url.openConnection();
	    conn.connect();
	    
	    instream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line = null;
	    while ((line = instream.readLine()) != null) {
		System.out.println(line);
	    }
	}
	catch (IOException exc) {
	    exc.printStackTrace();
	}
	finally {
	    try {
		if (instream != null) instream.close();
	    }
	    catch (Throwable t) {
		t.printStackTrace();
	    }
	}
    }
}

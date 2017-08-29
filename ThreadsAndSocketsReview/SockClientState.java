import java.net.*;
import java.io.*;

class SockClientState {
	public static void main (String args[]) throws Exception {
		Socket          sock = null;
		OutputStream    out = null;
		InputStream     in = null;
		int i1=0, i2=0;
		char cmd = ' ';

		if (args.length < 2 || args.length > 3) {
			System.out.println("USAGE: java SockClientState char [int1 int2]");
			System.exit(1);
		}

		try {
			sock = new Socket("localhost", 8888);
			out = sock.getOutputStream();
			in = sock.getInputStream();

			cmd = args[0].charAt(0);
			out.write(cmd);
			switch (cmd) {
			case 'r':
				i1 = Integer.parseInt(args[1]);
				out.write(i1);
				break;
			default: 
				i1 = Integer.parseInt(args[1]);
				out.write(i1);
				i2 = Integer.parseInt(args[2]);
				out.write(i2);
			}
			int result = in.read();
			System.out.println("Result is " + result);
		} catch (NumberFormatException nfe) {
			System.out.println("Command line args must be integers");
			System.exit(2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)  out.close();
			if (in != null)   in.close();
			if (sock != null) sock.close();
		}
	}
}

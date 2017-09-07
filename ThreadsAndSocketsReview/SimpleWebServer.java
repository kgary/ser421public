
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class SimpleWebServer {

    // 
    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Usage: SimpleWebServer <port>");
            System.exit(1);
        }

        SimpleWebServer server = new SimpleWebServer(Integer.parseInt(args[0]));

    }

    public SimpleWebServer(int port) {

        ServerSocket server = null;
        Socket sock = null;

        try {

            //*** Open the server socket on the specified port
            //*** Loop forever accepting socket requests
            //***   Get the response bytes from createResponse
            //***   Write the bytes to the socket's output stream
            //***   close streams and socket appropriately

            server = new ServerSocket(port);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        while (server.isBound() && !server.isClosed()) {
            System.out.println("Ready...");
            try {
                sock = server.accept();
                createClientThread(sock);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Take the newly accepted socket and make it a thread by passing it to
     * inner class ClientHandler which implements Runnable; subsequently start
     * the thread up so it can be processed.
     *
     * @param sock
     */
    private void createClientThread(Socket sock) {
        Thread thread = new Thread(new ClientHandler(sock));
        thread.start();
    }
}

/**
 * ClientHandler handles new client requests (threads). Accepts the
 * client socket as a constructor param, gets the IO streams and then calls the
 * overridden run() method to complete the transaction.
 *
 * @author kylej
 */
class ClientHandler implements Runnable {

    //establish a new socket to read client input from (via BufferedReader)
    InputStream in = null;
    OutputStream out = null;

    /**
     * ClientHandler is the constructor that accepts a client socket & chains it
     * to an input and output stream. These are used by the run method to create
     * a response for the client.
     *
     * @param clientSocket
     */
    public ClientHandler(Socket clientSocket) {
        try {
            //Set local socket to clientSocket received via constructor
            in = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//end constructor

    public byte[] createResponse(InputStream inStream) {

        byte[] response = null;
        BufferedReader in = null;

        try {

            // Read from socket's input stream.  Must use an
            // InputStreamReader to bridge from streams to a reader
            in = new BufferedReader(
                    new InputStreamReader(inStream, "UTF-8"));

            // Get header and save the filename from the GET line:
            //    example GET format: GET /index.html HTTP/1.1

            String filename = null;
            String line = in.readLine();
            System.out.println("Received: " + line);
            if (line != null && !line.trim().equals("")) {
                StringTokenizer st = new StringTokenizer(line);
                if (st.nextToken().equals("GET") && st.hasMoreTokens()) {
                    filename = st.nextToken();
                    if (filename.startsWith("/")) {
                        filename = filename.substring(1);
                    }
                }
            }
            System.out.println("FINISHED REQUEST, STARTING RESPONSE\n");

            // Generate an appropriate response to the user
            if (filename == null) {
                response =
                        "<html>Illegal request: no GET</html>".getBytes();
            } else {
                File file = new File(filename);
                if (!file.exists()) {
                    response = ("<html>File not found: "
                            + filename + "</html>").getBytes();
                } else {
                    response = readFileInBytes(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            response = ("<html>ERROR: "
                    + e.getMessage() + "</html").getBytes();
        }
        System.out.println("RESPONSE GENERATED!");
        return response;
    }

    /**
     * Read bytes from a file and return them in the byte array. We read in
     * blocks of 512 bytes for efficiency.
     */
    public static byte[] readFileInBytes(File f)
            throws IOException {

        byte[] result = new byte[(int) f.length()];

        try {
            new FileInputStream(f).read(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public void run() {
        System.out.println("Starting thread");
        try {
            out.write(createResponse(in));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Ending thread");
    }
}

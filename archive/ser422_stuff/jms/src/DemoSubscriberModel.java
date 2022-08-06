import javax.jms.*;
import javax.naming.*;
import org.apache.log4j.BasicConfigurator;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoSubscriberModel {
    private TopicSession pubSession;
    private TopicConnection connection;

    /* Establish JMS subscriber */
    public DemoSubscriberModel(String topicName, String clientName, String username, String password)
	throws Exception {
	// Obtain a JNDI connection
	InitialContext jndi = new InitialContext();
	// Look up a JMS connection factory
	TopicConnectionFactory conFactory = (TopicConnectionFactory)jndi.lookup("topicConnectionFactry");
	// Create a JMS connection
	connection = conFactory.createTopicConnection();
	connection.setClientID(clientName);  // this is normally done by configuration not programmatically
	// Look up a JMS topic - see jndi.properties in the classes directory
	Topic chatTopic = (Topic) jndi.lookup(topicName);
	TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	TopicSubscriber subscriber = subSession.createDurableSubscriber(chatTopic, "DemoSubscriberModel");
	// Start the JMS connection; allows messages to be delivered
	connection.start();
	// Listen for 10 messages
	int count=0;
        while (count < 10) {
            System.out.println("waiting for message...");
            TextMessage msg = (TextMessage) subscriber.receive();
            System.out.println(++count + "Received message: " + msg.getText());
	}
	connection.close();
    }
    
    public static void main(String[] args) {
	// uncomment this line for verbose logging to the screen
	// BasicConfigurator.configure();
	try {
	    if (args.length != 4)
		System.out.println("Please Provide the topic name,unique client id, username,password!");

	    DemoSubscriberModel demo = new DemoSubscriberModel(args[0], args[1], args[2], args[3]);
	    BufferedReader commandLine = new java.io.BufferedReader(new InputStreamReader(System.in));
	    
	    // closes the connection and exit the system when 'exit' enters in
	    // the command line
	    while (true) {
		String s = commandLine.readLine();
		if (s.equalsIgnoreCase("exit")) {
		    demo.connection.close();
		    System.exit(0);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

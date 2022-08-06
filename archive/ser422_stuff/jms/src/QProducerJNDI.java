import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.BasicConfigurator;

// from https://dzone.com/articles/jms-activemq
public class QProducerJNDI {
    public QProducerJNDI() throws JMSException, NamingException {
	// Obtain a JNDI connection
	InitialContext jndi = new InitialContext();
	// Look up a JMS connection factory
	ConnectionFactory conFactory = (ConnectionFactory) jndi.lookup("connectionFactory");
	Connection connection;
	// Getting JMS connection from the server and starting it
	connection = conFactory.createConnection();
	try {
	    connection.start();
	    // JMS messages are sent and received using a Session. We will
	    // create here a non-transactional session object. If you want
	    // to use transactions you should set the first parameter to 'true'
	    Session session = connection.createSession(false,
						       Session.AUTO_ACKNOWLEDGE);
	    Destination destination = (Destination) jndi.lookup("MyQueue");
	    // MessageProducer is used for sending messages (as opposed
	    // to MessageConsumer which is used for receiving them)
	    MessageProducer producer = session.createProducer(destination);
	    // We will send a small text message saying 'Hello World!'
	    TextMessage message = session.createTextMessage("Hello World!");
	    // Here we are sending the message!
	    producer.send(message);
	    System.out.println("Sent message '" + message.getText() + "' to queue example.MyQueue");
	} finally {
	    connection.close();
	}
    }
    public static void main(String[] args) throws JMSException {
	try {
	    // uncomment this if you want a lot of screen info about what activeMQ is doing
	    //	    BasicConfigurator.configure();
	    new QProducerJNDI();
	} catch (NamingException e) {
	    e.printStackTrace();
	}
    }
}

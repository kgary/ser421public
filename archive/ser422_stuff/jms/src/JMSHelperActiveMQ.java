import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

// This is the ActiveMQ version
public class JMSHelperActiveMQ {

    // URL if running in same VM
    // public static String jmsURL = "vm://localhost";
    // URL if running in different VMs
    public static String jmsURL = "tcp://localhost:61616";

    public static Connection getJMSConnection() throws Exception {
	// Create a ConnectionFactory
	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jmsURL);
	return connectionFactory.createConnection();
    }
}


package thread;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducerThread implements Runnable {

	@Override
	public void run() {
		try {
			//Creates factory
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://DESKTOP-1CETI38:61616");
			
			//Creates connection
			Connection connection = factory.createConnection();
			connection.start();
			
			//Creates session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//Creates destination
			Destination destination = session.createQueue("ThredQueue");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			String text = "Hello world thred test "+Thread.currentThread().getName()+":"+hashCode();
			TextMessage message = session.createTextMessage(text);
			
			System.out.println("Message to send thread producer "+text);
			producer.send(message);
			
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}

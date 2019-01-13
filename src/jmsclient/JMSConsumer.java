package jmsclient;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;



public class JMSConsumer {
	
	public static void main(String args) {
		
		try {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://DESKTOP-1CETI38:61616");
			
			Connection connection = factory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destnation = session.createQueue("MyFirstQueue");
			
			MessageConsumer consumer = session.createConsumer(destnation);
			
			Message message = consumer.receive(1000);
			
			if(message instanceof TextMessage) {
				TextMessage m = (TextMessage) message;
				String text = m.getText();
				System.out.println("Received: "+text);
			}else {
				System.out.println("Received: "+message);
			}
			consumer.close();
			session.close();
			connection.close();
			System.out.println("aaaaaaaaaaa");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

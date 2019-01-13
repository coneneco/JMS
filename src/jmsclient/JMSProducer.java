package jmsclient;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {
	
	public static void main(String[] args) {
		try {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://DESKTOP-1CETI38:61616");
			
			Connection connection = factory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue("MyFirstQueue");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			String text = "Sent to MyFirstQueue "+Thread.currentThread().getName();
			Message message = session.createTextMessage(text);
			
			System.out.println("Sent message--> "+message.hashCode()+":"+Thread.currentThread().getName());
			producer.send(message);
			
			session.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cought: "+e);
			e.printStackTrace();
		}
	}
}

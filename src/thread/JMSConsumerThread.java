package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumerThread implements Runnable, ExceptionListener{

	@Override
	public void run() {
		try {
			//Creates factory
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://DESKTOP-1CETI38:61616");
			
			Connection connection = factory.createConnection();
			connection.start();
			connection.setExceptionListener(this);
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue("ThredQueue");
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			Message message = consumer.receive();
			
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Consumer received: "+text);
			}else {
				System.out.println("Received: "+message);
			}
			
			consumer.close();
			session.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public synchronized void onException(JMSException arg0) {
		System.out.println("JMSException occuerd. Consumer shutting down!");
		
	}

}

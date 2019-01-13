package thread;

public class JMSThreadTest {

	public static void thread(Runnable r, boolean deamon) {
		Thread thread = new Thread(r);
		thread.setDaemon(deamon);
		thread.start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		thread(new JMSProducerThread(), false);
		thread(new JMSProducerThread(), false);
		thread(new JMSProducerThread(), false);
		thread(new JMSConsumerThread(), false);
		
		Thread.sleep(1000);
		
		thread(new JMSConsumerThread(), false);
		thread(new JMSProducerThread(), false);
		thread(new JMSConsumerThread(), false);
		thread(new JMSProducerThread(), false);
		thread(new JMSConsumerThread(), false);
		thread(new JMSProducerThread(), false);
		thread(new JMSConsumerThread(), false);
		
		Thread.sleep(1000);

	}

}

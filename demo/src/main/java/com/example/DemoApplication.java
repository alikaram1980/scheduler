package com.example;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.ProductRepository;
import com.example.entity.Product;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	  private static final String ORDER_QUEUE = "order-queue";
	  private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//productRepository.save(new Product("hp 675",900,12));
		//productRepository.save(new Product("samsung",500,27));
		//productRepository.save(new Product("apple",130,19));
		
		
		/* try {
             // Create a ConnectionFactory
             ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);

             // Create a Connection
             Connection connection = connectionFactory.createConnection();
             connection.start();

             // Create a Session
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

             // Create the destination (Topic or Queue)
             Destination destination = session.createQueue(ORDER_QUEUE);

             // Create a MessageProducer from the Session to the Topic or Queue
             MessageProducer producer = session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

             // Create a messages
             String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
             TextMessage message = session.createTextMessage(text);

             // Tell the producer to send the message
             System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
             producer.send(message);

             // Clean up
             session.close();
             connection.close();
         }
         catch (Exception e) {
             System.out.println("Caught: " + e);
             e.printStackTrace();
         }*/
	}
	
	
		
	
}


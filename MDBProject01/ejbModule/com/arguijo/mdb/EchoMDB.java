package com.arguijo.mdb;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

/**
 * Message-Driven Bean implementation class for: EchoMDB
 */
@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "Queue01"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, 
mappedName = "Queue01")
public class EchoMDB implements MessageListener {

	/**
	 * Default constructor.
	 */
	public EchoMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@Inject JMSContext jmsContext;
	@Resource(mappedName="Queue02") Queue queue02;
	public void onMessage(Message message) {
		try {
			String stringMessage = message.getBody(String.class);
			System.out.println("EchoMDB received the following message: " + stringMessage);
			TimeUnit.SECONDS.sleep(5);
			jmsContext.createProducer().send(queue02, "echo " + stringMessage);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package com.arguijo.mdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EchoMDBClient implements MessageListener {
	
	public static void main(String[] args) throws NamingException, IOException {
		EchoMDBClient echoMDBClient = new EchoMDBClient();
		Context context = EchoMDBClient.getInitialContext();
		Queue queue01 = (Queue)context.lookup("Queue01");
		Queue queue02 = (Queue)context.lookup("Queue02");
		JMSContext jmsContext = ((ConnectionFactory) context.lookup("jms/echoMDB")).createContext();
		jmsContext.createConsumer(queue02).setMessageListener(echoMDBClient);
		JMSProducer jmsProducer = jmsContext.createProducer();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String messageToSend = null;
		System.out.println("You are now connected...");
		while(true){
			messageToSend = bufferedReader.readLine();
			if(messageToSend.equalsIgnoreCase("exit")){
				jmsContext.close();
				System.out.println("Bye");
			} else {
				jmsProducer.send(queue01, messageToSend);
			}
		}
	}

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(message.getBody(String.class));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Context getInitialContext() throws NamingException {
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		properties.setProperty("java.naming.provider.url", "iiop://localhost:3700");
		return new InitialContext(properties);
	}
}

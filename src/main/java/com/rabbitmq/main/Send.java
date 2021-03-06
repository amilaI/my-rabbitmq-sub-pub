package com.rabbitmq.main;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Send {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException {

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");

			Connection connection = factory.newConnection();

			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);

			String message = "This is message 1";

			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");

			channel.close();

			connection.close();

		} catch (TimeoutException e) {

			e.printStackTrace();
		}

	}

}

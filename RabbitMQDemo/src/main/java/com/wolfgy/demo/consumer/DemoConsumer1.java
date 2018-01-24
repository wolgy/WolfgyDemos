package com.wolfgy.demo.consumer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.rabbitmq.client.Channel;

public class DemoConsumer1 {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RabbitListener(queues="WolfgyFirstDemoQueue")
	public void wolfgyFirstDemoQueue(Message message,Channel channel) throws UnsupportedEncodingException {
		String str = new String(message.getBody(), "utf-8");
		logger.info("WolfgyFirstDemoQueue收到消息:"+str);
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

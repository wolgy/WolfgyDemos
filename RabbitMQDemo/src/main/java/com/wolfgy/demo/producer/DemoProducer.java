package com.wolfgy.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoProducer {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	public void wolfgyFirstDemoQueue() {
        rabbitTemplate.convertAndSend("WolfgyFirstDemoQueue", "test msg");
        logger.info("消息发送成功");
	}
}

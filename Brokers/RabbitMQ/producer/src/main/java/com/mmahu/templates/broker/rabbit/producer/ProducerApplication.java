package com.mmahu.templates.broker.rabbit.producer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableRabbit
@SpringBootApplication
@RestController
public class ProducerApplication {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@RequestMapping("/")
	public void send() {
		rabbitTemplate.convertAndSend("amq.fanout","", "test");
	}

}

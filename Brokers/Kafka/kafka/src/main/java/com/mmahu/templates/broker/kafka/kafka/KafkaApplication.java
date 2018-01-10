package com.mmahu.templates.broker.kafka.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@RestController
@SpringBootApplication
public class KafkaApplication {

	@Autowired
	KafkaTemplate<String, String> template;

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@RequestMapping("/send")
    public void send() {
	    template.send("red","hello");
    }


    @KafkaListener(topics = "red")
    public void read(String message) {

    }

	@Bean
	public KafkaTemplate kafkaTemplate() {
		return new KafkaTemplate(new DefaultKafkaProducerFactory(producerConfigs()));
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		// list of host:port pairs used for establishing the initial connections to the Kakfa cluster
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return props;
	}
}

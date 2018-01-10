package com.mmahu.templates.redis;

import com.mmahu.templates.redis.controllers.AddressController;
import com.mmahu.templates.redis.model.Address;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisApplicationTests {

	@Autowired
	TestRestTemplate template;

	@Test
	public void contextLoads() {
		Address result = template.getForObject("/address/zip?code=2000", Address.class);
	}

	@After
	public void tearDown() throws Exception {
		ResponseEntity<Address[]> addresses = template.getForEntity("/address", Address[].class);
		Arrays.stream(addresses.getBody()).forEach(p -> template.delete("/address"));
	}

	@Test
	public void testRedisList() {
		template.put("/address", new Address("balts", "100"));

		ResponseEntity<Address[]> addresses = template.getForEntity("/address", Address[].class);

		Stream<Address> addressStream = Arrays.stream(addresses.getBody());
		boolean balts = addressStream.anyMatch(p -> p.getCite().equals("balts"));

		Assert.assertTrue(balts);
	}
}



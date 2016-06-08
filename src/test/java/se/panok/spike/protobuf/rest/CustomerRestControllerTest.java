package se.panok.spike.protobuf.rest;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import se.panok.spike.protobuf.Application;
import se.panok.spike.protobuf.model.CustomerProtos.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port: 0" })
public class CustomerRestControllerTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${local.server.port}")
	private int port;

	@Test
	public void contextLoaded() {

		Random rand = new Random();
		int id = rand.nextInt(4) + 1;

		ResponseEntity<Customer> customer = restTemplate.getForEntity("http://127.0.0.1:" + port + "/customers/" + id,
				Customer.class);

		logger.info("Retrieved response = {}", customer);

		assertNotNull(customer);
		assertEquals(id, customer.getBody().getId());
	}

	@Configuration
	public static class RestClientConfiguration {

		@Bean
		RestTemplate restTemplate(final ProtobufHttpMessageConverter hmc) {
			return new RestTemplate(Arrays.asList(hmc));
		}

		@Bean
		ProtobufHttpMessageConverter protobufHttpMessageConverter() {
			return new ProtobufHttpMessageConverter();
		}
	}

}

package org.sapient.microservices.price;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(ProductPriceConfigurer.class)
public class ProductPriceServer {

	@Autowired
	protected ProductPriceRepository productPriceRepository;

	protected Logger logger = Logger.getLogger(ProductPriceServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// product-server.yml
		System.setProperty("spring.config.name", "price-server");

		SpringApplication.run(ProductPriceServer.class, args);
	}
}

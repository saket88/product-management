package org.sapient.microservices.product;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(ProductConfigurer.class)
public class ProductServer {
	
	@Autowired
	protected ProductRepository productRepository;

	protected Logger logger = Logger.getLogger(ProductServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// product-server.yml
		System.setProperty("spring.config.name", "product-server");

		SpringApplication.run(ProductServer.class, args);
	}

}

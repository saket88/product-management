package org.sapient.microservices.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
// Disable component scanner ...
@ComponentScan(useDefaultFilters = false)
public class ResourceServer {
	

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String PRODUCT_SERVICE_URL = "http://PRODUCT-SERVICE";
	public static final String PRICING_SERVICE_URL = "http://PRICE-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "resource-server");
		SpringApplication.run(ResourceServer.class, args);
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public ResourceProductService productsService() {
		return new ResourceProductService(PRODUCT_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 */
	@Bean
	public ResourceProductController productController() {
		return new ResourceProductController(productsService());
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public ResourcePricingService pricingService() {
		return new ResourcePricingService(PRICING_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 */
	@Bean
	public ResourcePricingController productPriceController() {
		return new ResourcePricingController(pricingService());
	}

}

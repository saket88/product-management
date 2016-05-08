package org.sapient.microservices.price;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@SpringBootApplication
@EntityScan("org.sapient.microservices.price")
@EnableJpaRepositories("org.sapient.microservices.price")
@PropertySource("classpath:db-config.properties")
public class ProductPriceConfigurer {



	protected Logger logger = Logger.getLogger(ProductPriceConfigurer.class
			.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductPriceConfigurer.class, args);
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.addScript("classpath:testdatabase/schema-product-pricing.sql")
				.addScript("classpath:testdatabase/data-product-pricing.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> productprices = jdbcTemplate
				.queryForList("SELECT product_code FROM T_PRODUCT_PRICE");
		logger.info("System has " + productprices.size() + " productprices");
		
		return dataSource;
	}

}

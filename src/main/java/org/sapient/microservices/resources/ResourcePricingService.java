package org.sapient.microservices.resources;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.sapient.microservices.price.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourcePricingService {

	@LoadBalanced
	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(ResourcePricingService.class
			.getName());

	public ResourcePricingService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public BigDecimal findByCode(String code) {
		ProductPrice productPrice = null;

		try {
			productPrice = restTemplate.getForObject(serviceUrl
					+ "/productPrice/code/{code}", ProductPrice.class, code);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (productPrice == null )
			return null;
		else
			return productPrice.getPrice();
	}
}

package org.sapient.microservices.resources;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.sapient.microservices.product.Product;
import org.sapient.microservices.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@Service
public class ResourceProductService {


	@LoadBalanced
	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(ResourceProductService.class
			.getName());

	public ResourceProductService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	@ResponseBody
	public void save(ProductDTO product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ProductDTO> entity = new HttpEntity<ProductDTO>(product,headers);
		logger.info("Service Url "+serviceUrl);
	     restTemplate.postForObject(serviceUrl+"/product", entity,ProductDTO.class);
	}
	
	@ResponseBody
	public void delete(String id) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", id);
		logger.info("Service Url "+serviceUrl);
	     restTemplate.delete(serviceUrl+ "/product/{id}", params);
	}
	public List<Product> findByType(String type) {
		Product[] products = null;

		try {
			products = restTemplate.getForObject(serviceUrl
					+ "/products/type/{type}", Product[].class, type);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (products == null || products.length == 0)
			return null;
		else
			return Arrays.asList(products);
	}
}

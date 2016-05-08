package org.sapient.microservices.resources;

import java.util.List;
import java.util.logging.Logger;

import org.sapient.microservices.product.Product;
import org.sapient.microservices.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourceProductController {

	@Autowired
	protected ResourceProductService productsService;

	protected Logger logger = Logger.getLogger(ResourceProductController.class
			.getName());

	public ResourceProductController(ResourceProductService productsService) {
		this.productsService = productsService;
	} 

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@ResponseBody
	public void save(@RequestBody ProductDTO productDTO) {
		productsService.save(productDTO);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Product> delete(@PathVariable("id") String id) {
		productsService.delete(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/products/type/{type}", method = RequestMethod.GET)
	public @ResponseBody List<Product> byType(@PathVariable("type") String type) {
		return productsService.findByType(type);
		
	}

}

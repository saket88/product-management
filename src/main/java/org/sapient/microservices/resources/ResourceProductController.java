package org.sapient.microservices.resources;

import java.util.logging.Logger;

import org.sapient.microservices.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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



}

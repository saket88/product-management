package org.sapient.microservices.product;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 * A RESTFul controller for accessing account information.
 * 
 * @author Saket Puranik
 */
@RestController
public class ProductController {
	protected Logger logger = Logger.getLogger(ProductController.class
			.getName());
	
	@Autowired
	protected ProductRepository productRepository;
	

	/**
	 * Create an instance plugging in the respository of Products.
	 * 
	 * @param productRepositry
	 *            A product repository implementation.
	 */
	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;

		logger.info("AccountRepository says system has "
				+ productRepository.countProducts() + " products");
	}
	

	/**
	 * Saves the product
	 * @param productDTO
	 * @return  void
	 */
	@RequestMapping(value="/product",method=RequestMethod.POST)
	public void save(@RequestBody ProductDTO productDTO) {
		logger.info("products-service saved "
				+ productRepository.getClass().getName() );
		Product product=  new Product() ;
		product.setCode(productDTO.getCode());
		product.setName(productDTO.getName());
		product.setType(productDTO.getType());
		productRepository.save(product);

	}

}

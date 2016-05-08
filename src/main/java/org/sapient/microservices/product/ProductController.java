package org.sapient.microservices.product;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 * A RESTFul controller for accessing product information.
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

		logger.info("ProductRepository says system has "
				+ productRepository.countProducts() + " products");
	}
	

	/**
	 * Saves the product
	 * @param productDTO
	 * @return  void
	 */
	@RequestMapping(value="/product",method=RequestMethod.POST)
	public void save(@RequestBody ProductDTO productDTO) {
		logger.info("products-service save "
				+ productRepository.getClass().getName() );
		Product product=  new Product() ;
		product.setCode(productDTO.getCode());
		product.setName(productDTO.getName());
		product.setType(productDTO.getType());
		productRepository.save(product);

	}

	/**
	 * Deletes the product
	 * @param String id
	 * @return  void
	 */
	@RequestMapping(value="/product/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		logger.info("products-service delete ");
		productRepository.delete(Long.valueOf(id));

	}
	

	/**
	 * Deletes the product
	 * @param String productCode) 
	 * @return  void
	 */
	@RequestMapping(value="/products/type/{type}",method=RequestMethod.GET)
	public List<Product> byType(@PathVariable("type") String type) {
		logger.info("products-service byType() invoked: "
				+ productRepository.getClass().getName() + " for "
				+ type);

		List<Product> products = productRepository
				.findByType(type);
		logger.info("products-service byType() found: " + products);

		if (products == null || products.size() == 0)
			throw new ProductNotFoundException(type);
		else {
			return products;
		}
	}

}


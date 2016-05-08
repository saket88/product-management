package org.sapient.microservices.price;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductPriceController {

	protected Logger logger = Logger.getLogger(ProductPriceController.class
			.getName());
	
	@Autowired
	protected ProductPriceRepository productPriceRepository;
	
	

	/**
	 * Create an instance plugging in the repository of Product prices.
	 * 
	 * @param productPriceRepositry
	 *            A product price repository implementation.
	 */
	@Autowired
	public ProductPriceController(ProductPriceRepository productPriceRepository) {
		this.productPriceRepository = productPriceRepository;

		logger.info("ProductRepository says system has "
				+ productPriceRepository.countProductPrices() + " product prices");
	}
	

	/**
	 * find the product by code
	 * @param String productCode) 
	 * @return  ProductPrice class
	 */
	@RequestMapping(value="/productPrice/code/{code}",method=RequestMethod.GET)
	public ProductPrice byCode(@PathVariable("code") String code) {
		logger.info("pricing-service byCode() invoked: "
				+ productPriceRepository.getClass().getName() + " for "
				+ code);

		ProductPrice productPrice = productPriceRepository.findByCode(code);
		logger.info("pricing-service byCode() found: " + productPrice);

		if (productPrice == null )
			throw new ProductPriceNotFoundException(code);
		else {
			return productPrice;
		}
	}
	

	
}

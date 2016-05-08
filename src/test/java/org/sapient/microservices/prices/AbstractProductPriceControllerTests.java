package org.sapient.microservices.prices;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.sapient.microservices.price.ProductPrice;
import org.sapient.microservices.price.ProductPriceController;
import org.sapient.microservices.price.ProductPriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractProductPriceControllerTests {
	@Autowired
	ProductPriceController productPriceController;
	
	protected static final String PRODUCT_CODE = "123456006";
	
	@Test
	public void validProductPrice(){

		Logger.getGlobal().info("Start validProductPrice test");
		ProductPrice productPrice = productPriceController.byCode(PRODUCT_CODE);

		Assert.assertNotNull(productPrice);
		Assert.assertEquals(PRODUCT_CODE, productPrice.getCode());
		Logger.getGlobal().info("End validProductPrice test");
	
	}

	@Test(expected=ProductPriceNotFoundException.class)
	public void invalidProductPrice(){

		Logger.getGlobal().info("Start invalidProductPrice test");
		ProductPrice productPrice = productPriceController.byCode("some_random_code");

		Assert.assertNull(productPrice);
		Logger.getGlobal().info("End invalidProductPrice test");
	
	}
}

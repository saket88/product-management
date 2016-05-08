package org.sapient.microservices.products;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.sapient.microservices.product.Product;
import org.sapient.microservices.product.ProductController;
import org.sapient.microservices.product.ProductDTO;
import org.sapient.microservices.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractProductControllerTests {
	
	@Autowired
	ProductController productController;
	
	protected static final String PRODUCT_TYPE = "TEST TYPE";
	protected static final String TEST_SCRIPT_PRODUCT_TYPE = "Detergent";
	
	@Test(expected=ProductNotFoundException.class)
	public void testSave(){

		Logger.getGlobal().info("Start testSave test");
		
		//set the new product
		ProductDTO product = initiatlizeProduct();
		
		//check that that TYPE not exists 
		List<Product> products = productController.byType(PRODUCT_TYPE);
		Assert.assertNull(products);
		
		//create the product
		productController.save(product);

		//Again check whether it is saved
		products = productController.byType(PRODUCT_TYPE);
		Assert.assertNotNull(products);
		Logger.getGlobal().info("End testSave test");
	
	}

	private ProductDTO initiatlizeProduct() {
		ProductDTO product = new ProductDTO();
		product.setCode("TEST1234");
		product.setType("TEST TYPE");
		product.setName("TEST NAME");
		return product;
	}

	@Test
	public void testDelete(){

		Logger.getGlobal().info("Start testDelete test");
		
		List<Product> products = productController.byType(TEST_SCRIPT_PRODUCT_TYPE);
		int size = products.size();
		Product product = products.get(0);
		
		productController.delete(String.valueOf(product.getId()));
		
		List<Product> productsAgain = productController.byType(TEST_SCRIPT_PRODUCT_TYPE);
		int sizeAgain=productsAgain.size();
		int differnce =size-sizeAgain;
		
		Assert.assertEquals(1, differnce);

		Logger.getGlobal().info("End testDelete test");
	
	}
	
	@Test
	public void validProductByType(){

		Logger.getGlobal().info("Start validProductByType test");
		List<Product> products = productController.byType(TEST_SCRIPT_PRODUCT_TYPE);
		Assert.assertNotNull(products);
		Logger.getGlobal().info("End validProductByType test");
	
	}

	@Test(expected=ProductNotFoundException.class)
	public void invalidProductByType(){

		Logger.getGlobal().info("Start invalidProductByType test");
		List<Product> products = productController.byType(PRODUCT_TYPE);
		Assert.assertNull(products);
		Logger.getGlobal().info("End invalidProductByType test");
	}
	
}

package org.sapient.microservices.products;

import org.junit.runner.RunWith;
import org.sapient.microservices.product.ProductConfigurer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductConfigurer.class)
public class ProductControllerIntegrationTests extends AbstractProductControllerTests{

}

package org.sapient.microservices.prices;

import org.junit.runner.RunWith;
import org.sapient.microservices.price.ProductPriceConfigurer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductPriceConfigurer.class)
public class ProductPriceControllerIntegrationTests extends AbstractProductPriceControllerTests{

}

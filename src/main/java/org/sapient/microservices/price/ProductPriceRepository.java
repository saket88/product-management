package org.sapient.microservices.price;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ProductPriceRepository extends Repository<ProductPrice, Long>{


	/**
	 * Fetch the number of Product prices known to the system.
	 * 
	 * @return The number of Product prices
	 */
	@Query("SELECT count(*) from ProductPrice")
	public int countProductPrices();
	
	/**
	 * Find  product price  with the specified  product code.
	 *
	 * @param accountNumber
	 * @return The product price if found, null otherwise.
	 */
	public ProductPrice findByCode(String code);
}

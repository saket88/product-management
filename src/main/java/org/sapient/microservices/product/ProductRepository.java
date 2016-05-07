package org.sapient.microservices.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long>{
	/**
	 * Find an product with the specified product code.
	 *
	 * @param productCode
	 * @return The account if found, null otherwise.
	 */
	public Product findByCode(String productCode);

	/**
	 * Find Products whose owner name contains the specified string
	 * 
	 * @param name
	 *            Any alphabetic string.
	 * @return The list of matching Products - always non-null, but may be
	 *         empty.
	 */
	public List<Product> findByNameContainingIgnoreCase(String name);

	/**
	 * Fetch the number of Products known to the system.
	 * 
	 * @return The number of Products.
	 */
	@Query("SELECT count(*) from Product")
	public int countProducts();


}

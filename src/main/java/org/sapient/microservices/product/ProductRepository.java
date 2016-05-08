package org.sapient.microservices.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long>{
	
	/**
	 * Fetch the number of Products known to the system.
	 * 
	 * @return The number of Products.
	 */
	@Query("SELECT count(*) from Product")
	public int countProducts();
	
	/**
	 * Find  products  with the specified  product Type.
	 *
	 * @param accountNumber
	 * @return The products if found, null otherwise.
	 */
	public List<Product> findByType(String type);


}

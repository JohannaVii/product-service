package se.iths.johanna.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.johanna.productservice.entity.Product;

/**
 * Hanterar hämtning och lagring av produkter i db
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}

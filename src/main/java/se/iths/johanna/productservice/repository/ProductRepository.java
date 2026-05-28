package se.iths.johanna.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.johanna.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsById(Long id);
}

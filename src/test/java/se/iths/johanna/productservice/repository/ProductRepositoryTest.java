package se.iths.johanna.productservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.iths.johanna.productservice.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProductRepositoryTest {

    // Variabel
    @Autowired
    private ProductRepository repository;

    // Test - Att spara och hämta produkt
    @Test
    void shouldSaveAndFindProduct() {
        Product product = new Product("A", "Description", BigDecimal.valueOf(10), 5);
        Product saved = repository.save(product);

        Optional<Product> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("A", found.get().getName());
    }

    // Test - Att ta bort produkt
    @Test
    void shouldDeleteProduct() {
        Product product = repository.save(new Product("A", "Description", BigDecimal.valueOf(10), 5));

        repository.deleteById(product.getId());
        Optional<Product> found = repository.findById(product.getId());

        assertTrue(found.isEmpty());
    }
}

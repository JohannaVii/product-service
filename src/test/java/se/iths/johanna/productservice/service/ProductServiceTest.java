package se.iths.johanna.productservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.johanna.productservice.dto.OrderRequestItem;
import se.iths.johanna.productservice.dto.ProductInfo;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.entity.Product;
import se.iths.johanna.productservice.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    // Variabler
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    // Test - Att skapa produkt
    @Test
    void shouldCreateProduct() {

        // Arrange
        ProductRequestDto dto = new ProductRequestDto(
                "Test",
                "Description",
                BigDecimal.valueOf(10),
                5
        );

        Product saved = new Product("Test", "Description", BigDecimal.valueOf(10), 5);
        when(repository.save(any(Product.class))).thenReturn(saved);

        // Act
        ProductResponseDto result = service.createProduct(dto);

        // Assert
        assertEquals("Test", result.getName());
    }

    // Test - Att hämta alla produkter
    @Test
    void shouldReturnAllProducts() {

        // Arrange
        Product product1 = new Product("A", "Description1", BigDecimal.valueOf(10), 3);
        Product product2 = new Product("B", "Description2", BigDecimal.valueOf(20), 6);

        when(repository.findAll()).thenReturn(List.of(product1, product2));

        // Act
        List<ProductResponseDto> result = service.getAllProducts();

        // Assert
        assertEquals(2, result.size());
    }

    // Test - Att hämta produkt (Id)
    @Test
    void shouldReturnProductById() {

        // Arrange
        Product product1 = new Product("A", "Description1", BigDecimal.valueOf(10), 3);
        when(repository.findById(1L)).thenReturn(Optional.of(product1));

        // Act
        ProductResponseDto result = service.getProductById(1L);

        // Assert
        assertEquals("A", result.getName());
    }

    // Test - Att fel kastas vid saknat id
    @Test
    void shouldThrowWhenProductNotFound() {

        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> service.getProductById(1L));
    }

    // Test - Att ta bort produkt
    @Test
    void shouldDeleteProduct() {

        // Arrange
        when(repository.existsById(1L)).thenReturn(true);

        // Act
        service.deleteProduct(1L);

        // Assert
        verify(repository, times(1)).deleteById(1L);
    }

    // Test - Att fel kastas vid delete
    @Test
    void shouldThrowWhenDeleteProductNotFound() {

        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> service.deleteProduct(1L));
    }

    // Test - Att minska lagersaldo (Order)
    @Test
    void shouldUpdateStock() {

        // Arrange
        OrderRequestItem item = new OrderRequestItem(1L, 2);

        Product product1 = new Product("A", "Description", BigDecimal.valueOf(10), 5);
        product1.setStock(5);

        when(repository.findAllById(List.of(1L))).thenReturn(List.of(product1));

        // Act
        List<ProductInfo> result = service.updatedStock(List.of(item));

        // Assert
        assertEquals(3, product1.getStock());
        verify(repository, times(1)).saveAll(anyList());
    }
}

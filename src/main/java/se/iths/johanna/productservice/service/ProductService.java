package se.iths.johanna.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.iths.johanna.productservice.dto.OrderRequestItem;
import se.iths.johanna.productservice.dto.ProductInfo;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    // Metod - Skapa produkt
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Product saved = new Product(
                1L,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stock()
        );

        return new ProductResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStock()
        );
    }

    // Metod - Hämta alla produkter
    public List<ProductResponseDto> getAllProducts() {
        return List.of(
                new ProductResponseDto(1L, "Mock Product 1", "Test", new BigDecimal("99.90"), 10),
                new ProductResponseDto(2L, "Mock Product 2", "Test", new BigDecimal("149.90"), 5)
        );
    }

    // Metod - Hämta produkt (id)
    public ProductResponseDto getProductById(Long id) {

        return new ProductResponseDto(
                id,
                "Mock Product",
                "Test",
                new BigDecimal("99.90"),
                10
        );
    }

    // Metod - Ta bort produkt
    public void deleteProduct(Long id) {
    }

    // Metod - Minska antal produkter (order)
    @Transactional
    public List<ProductInfo> updatedStock(List<OrderRequestItem> items) {

        return items.stream()
                .map(i -> new ProductInfo(
                        i.getProductId(),
                        "Mock Product",
                        new BigDecimal("99.90"),
                        i.getQuantity()
                ))
                .toList();
    }
}
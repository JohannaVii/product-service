package se.iths.johanna.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.iths.johanna.productservice.dto.OrderRequestItem;
import se.iths.johanna.productservice.dto.ProductInfo;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.repository.ProductRepository;

import java.util.List;

// import se.iths.johanna.productservice.model.Product;

@Service
public class ProductService {

    // Variabel
    private final ProductRepository repository;

    // Konstruktor
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Metod - Skapa produkt
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        return null;
    }

    // Metod - Hämta alla produkter
    public List<ProductResponseDto> getAllProducts() {
        return null;
    }

    // Metod - Hämta produkt (id)
    public ProductResponseDto getProductById(Long id) {
        return null;
    }

    // Metod - Ta bort produkt
    public void deleteProduct(Long id) {
    }

    // Metod - Minska antal produkter (order)
    @Transactional
    public List<ProductInfo> decreaseStock(List<OrderRequestItem> items) {
        return null;
    }
}
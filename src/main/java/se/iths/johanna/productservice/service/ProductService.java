package se.iths.johanna.productservice.service;

import org.springframework.stereotype.Service;
import se.iths.johanna.productservice.repository.ProductRepository;

// import se.iths.johanna.productservice.dto.ProductRequestDto;
// import se.iths.johanna.productservice.dto.ProductResponseDto;
// import se.iths.johanna.productservice.dto.ProductInfo;
// import se.iths.johanna.productservice.dto.OrderRequestItem;

// import se.iths.johanna.productservice.model.Product;

@Service
public class ProductService {

    // Variabel
    private final ProductRepository productRepository;

    // Konstruktor
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Metod - Skapa produkt
    //public ProductResponseDto createProduct(ProductRequestDto dto) {
    // }

    // Metod - Hämta alla produkter
    // public List <ProductResponseDto> getAllProducts() {
    // }

    // Metod - Hämta produkt (id)
    // public ProductResponseDto getProductById(Long id) {
    // }

    // Metod - Ta bort produkt
    // public void deleteProduct (Long id) {
    // }

    // Metod - Minska antal produkter (order)
    // @Transactional
    //public List <ProductInfo> decreaseStock (List<OrderRequestItem> items) {
    // }

}
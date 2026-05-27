package se.iths.johanna.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.iths.johanna.productservice.dto.OrderRequestItem;
import se.iths.johanna.productservice.dto.ProductInfo;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.model.Product;
import se.iths.johanna.productservice.repository.ProductRepository;

import java.util.List;

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
        Product product = new Product(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock()
        );

        Product saved = repository.save(product);

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
        return repository.findAll()
                .stream()
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock()
                ))
                .toList();
    }

    // Metod - Hämta produkt (id)
    public ProductResponseDto getProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkten du söker hittades inte!"));

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    // Metod - Ta bort produkt
    public void deleteProduct(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Produkten du söker hittades inte!");
        }
        repository.deleteById(id);
    }

    // Metod - Minska antal produkter (order)
    @Transactional
    public List<ProductInfo> updatedStock(List<OrderRequestItem> items) {
        List<Long> ids = items.stream()
                .map(OrderRequestItem::getProductId)
                .toList();

        List<Product> products = repository.findAllById(ids);

        if (products.size() != items.size()) {
            throw new RuntimeException("Produkter saknas!");
        }

        for (Product product : products) {

            int requested = items.stream()
                    .filter(i -> i.getProductId().equals(product.getId()))
                    .findFirst()
                    .get()
                    .getQuantity();

            if (product.getStock() < requested) {
                throw new RuntimeException("Inte tillräckligt i lager!");
            }
        }

        for (Product product : products) {

            int requested = items.stream()
                    .filter(i -> i.getProductId().equals(product.getId()))
                    .findFirst()
                    .get()
                    .getQuantity();

            product.setStock(product.getStock() - requested);
        }

        repository.saveAll(products);

        return products.stream()
                .map(product -> {

                    int quantity = items.stream()
                            .filter(i -> i.getProductId().equals(product.getId()))
                            .findFirst()
                            .get()
                            .getQuantity();

                    return new ProductInfo(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            quantity
                    );
                })
                .toList();
    }
}
package se.iths.johanna.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.iths.johanna.productservice.dto.OrderRequestItem;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.entity.Product;
import se.iths.johanna.productservice.exception.InsufficientStockException;
import se.iths.johanna.productservice.exception.ProductNotFoundException;
import se.iths.johanna.productservice.mapper.ProductMapper;
import se.iths.johanna.productservice.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    // Variabel
    private final ProductRepository repository;
    private final ProductMapper mapper;

    // Konstruktor
    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Metod - Skapa produkt
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Product newProduct = new Product(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stock()
        );

        Product saved = repository.save(newProduct);

        return mapper.toResponse(saved);

    }

    // Metod - Hämta alla produkter
    public List<ProductResponseDto> getAllProducts() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    // Metod - Hämta produkt (id)
    public ProductResponseDto getProductById(Long id) {

        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produkt hittades inte!"));

        return mapper.toResponse(product);
    }

    // Metod - Ta bort produkt
    public void deleteProduct(Long id) {

        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Produkt hittades inte!");
        }
        repository.deleteById(id);
    }

    // Metod - Minska antal produkter (order)
    @Transactional
    public List<ProductResponseDto> decreaseStock(List<OrderRequestItem> items) {

        List<Product> products = items.stream().map(req -> {

            Product product = repository.findById(req.getProductId()).orElseThrow(() -> new ProductNotFoundException("Produkt hittades inte!"));

            if (product.getStock() < req.getQuantity()) {
                throw new InsufficientStockException("Inte tillräckligt lagersaldo!");
            }
            return product;
        }).toList();

        List<Product> updatedStock = items.stream().map(req -> {

            Product product = products.stream().filter(p -> p.getId().equals(req.getProductId())).findFirst().get();

            product.setStock(product.getStock() - req.getQuantity());

            return repository.save(product);
        }).toList();

        return updatedStock.stream().map(mapper::toResponse).toList();
    }
}
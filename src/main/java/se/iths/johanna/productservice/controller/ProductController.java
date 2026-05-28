package se.iths.johanna.productservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Variabel
    private final ProductService service;

    // Konstruktor
    public ProductController(ProductService service) {
        this.service = service;
    }

    // Metod - Skapa produkt (POST)
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto created = service.createProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Metod - Lista produkter (GET)
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {

        return ResponseEntity.ok(service.getAllProducts());
    }

    // Metod - Hämta produkter (id) (GET)
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getProductById(id));
    }

    // Metod - Ta bort produkt (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}

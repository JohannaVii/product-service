package se.iths.johanna.productservice.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.johanna.productservice.dto.OrderRequestItem;
import se.iths.johanna.productservice.dto.ProductInfo;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "authorization")
public class ProductController {

    // Variabel
    private final ProductService service;

    // hemlighet som används för interna anrop från Order Service.
    private final String internalServiceToken;

    // Konstruktor: Spring hämtar hemligheten från konfigurationen
    public ProductController(ProductService service, @Value("${internal.service-token}") String internalServiceToken) {
        this.service = service;
        this.internalServiceToken = internalServiceToken;
    }

    // Metod - Skapa produkt (POST)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto created = service.createProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Metod - Lista produkter (GET)
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
    }

    // Metod - Hämta produkter (id) (GET)
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));
    }

    // Metod - Ta bort produkt (DELETE)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Metod - Minska lagersaldo (POST)
    @PostMapping("/stock/decrease")
    public ResponseEntity<List<ProductInfo>> decreaseStock(@RequestBody List<OrderRequestItem> items) {

        return ResponseEntity.ok(service.decreaseStock(items));
    }


    // återställer lagersaldot när en Stripe-checkout löper ut
    // endast anrop med rätt intern hemlighet får återställa lagret
    @PostMapping("/stock/restore")
    public ResponseEntity<Void> restoreStock(
            @RequestBody List<OrderRequestItem> items,
            @RequestHeader(
                    value = "X-Internal-Service-Token",
                    required = false) String providedToken) {

        // avvisa anrop som saknar rätt intern hemlighet
        if (!internalServiceToken.equals(providedToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // återställ lagret först när hemligheten har kontrollerats
        service.restoreStock(items);

        return ResponseEntity.noContent().build();
    }

}

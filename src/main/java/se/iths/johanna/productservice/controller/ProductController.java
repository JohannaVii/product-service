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
import se.iths.johanna.productservice.exception.InsufficientStockException;
import se.iths.johanna.productservice.exception.ProductNotFoundException;
import se.iths.johanna.productservice.service.ProductService;

import java.util.List;

/**
 * Hanterar produkter och lagersaldo
 */
@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "authorization")
public class ProductController {

    // Variabel
    private final ProductService service;

    // Hemlighet som används för interna anrop från Order Service.
    private final String internalServiceToken;

    /**
     * Konstruktor för ProductController
     *
     * @param service              används för logiken kring produkter och lagersaldo
     * @param internalServiceToken används för att kontrollera interna anrop
     */
    public ProductController(ProductService service, @Value("${internal.service-token}") String internalServiceToken) {
        this.service = service;
        this.internalServiceToken = internalServiceToken;
    }

    /**
     * Skapar en ny produkt
     *
     * @param dto innehåller information om produkten som ska skapas
     * @return den skapade produkten (ProductResponseDto)
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto created = service.createProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Hämtar alla produkter
     *
     * @return en lista med alla produkter (ProductResponseDto)
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
    }

    /**
     * Hämtar en produkt med id
     *
     * @param id används för att hitta rätt produkt
     * @return produkt med id (ProductResponseDto)
     * @throws ProductNotFoundException om produkten inte finns
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));
    }

    /**
     * Tar bort en produkt med id
     *
     * @param id används för att hitta produkten som ska tas bort
     * @return ett tomt svar (ResponseEntity)
     * @throws ProductNotFoundException om produkten inte finns
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Minskar lagersaldot i en order
     *
     * @param items innehåller produkter och antal som ska dras från lagret
     * @return information om produkterna i ordern (ProductInfo)
     * @throws ProductNotFoundException   om en produkt inte finns
     * @throws InsufficientStockException om lagersaldot inte räcker
     */
    @PostMapping("/stock/decrease")
    public ResponseEntity<List<ProductInfo>> decreaseStock(@RequestBody List<OrderRequestItem> items) {

        return ResponseEntity.ok(service.decreaseStock(items));
    }


    /**
     * Återställer lagersaldot
     *
     * @param items         innehåller produkter och antal som ska läggas tillbaka i lagret
     * @param providedToken används för att kontrollera att anropet är internt
     * @return ett tomt svar (ResponseEntity)
     * @throws ProductNotFoundException om en produkt inte finns
     * @throws IllegalArgumentException om antalet är 0 eller mindre
     */
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

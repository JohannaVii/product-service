package se.iths.johanna.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.entity.Category;
import se.iths.johanna.productservice.entity.Product;
import se.iths.johanna.productservice.repository.ProductRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductIntegrationTest {

    // Variabler
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductRepository repository;

    // Metod - Rensa (db) innan varje test
    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    // Test - Skapa produkt
    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequestDto dto = new ProductRequestDto(
                "Necklace-1",
                "En beskrivning till Necklace-1",
                BigDecimal.valueOf(100.00),
                10,
                Category.NECKLACES,
                "/images/necklaces/necklace-1.avif"
        );

        mvc.perform(post("/products")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))).contentType("application/json")
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Necklace-1"))
                .andExpect(jsonPath("$.category").value("NECKLACES"))
                .andExpect(jsonPath("$.imageUrl").value("/images/necklaces/necklace-1.avif"));
    }

    // Test - Lista alla produkter
    @Test
    void shouldListAllProducts() throws Exception {

        repository.save(new Product("Necklace-1", "En beskrivning till Necklace-1", BigDecimal.valueOf(100.00), 10, Category.NECKLACES, "/images/necklaces/necklace-1.avif"));
        repository.save(new Product("Bracelet-1", "En beskrivning till Bracelet-1", BigDecimal.valueOf(100.00), 10, Category.BRACELETS, "/images/bracelets/bracelet-1.avif"));

        mvc.perform(get("/products").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].category").exists())
                .andExpect(jsonPath("$[0].imageUrl").exists());
    }

    // Test - Hämta produkt (id)
    @Test
    void shouldGetProductById() throws Exception {

        Product saved = repository.save(
                new Product("Earrings-1", "En beskrivning till Earrings-1", BigDecimal.valueOf(100.00), 10, Category.EARRINGS, "/images/earrings/earrings-1.avif")
        );

        mvc.perform(get("/products/{id}", saved.getId()).with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Earrings-1"))
                .andExpect(jsonPath("$.category").value("EARRINGS"))
                .andExpect(jsonPath("$.imageUrl").value("/images/earrings/earrings-1.avif"));
    }

    // Test - Ta bort produkt
    @Test
    void shouldDeleteProduct() throws Exception {

        Product saved = repository.save(
                new Product("Ring-1", "En beskrivning till Ring-1", BigDecimal.valueOf(100.00), 10, Category.RINGS, "/images/rings/ring-1.avif")
        );

        mvc.perform(delete("/products/{id}", saved.getId()).with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(status().isNoContent());
    }

    // Test - Minska lagersaldo
    @Test
    void shouldDecreaseStock() throws Exception {

        Product saved = repository.save(
                new Product("Bracelet-1", "En beskrivning till Bracelet-1", BigDecimal.valueOf(100.00), 10, Category.BRACELETS, "/images/bracelets/bracelet-1.avif")
        );

        String json = """
                [
                    {
                        "productId": %d,
                        "quantity": 2
                    }
                ]
                """.formatted(saved.getId());
        mvc.perform(post("/products/stock/decrease").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quantity").value(2));

        Product updated = repository.findById(saved.getId()).orElseThrow();

        assertEquals(8, updated.getStock());
    }

    // Test - Fel kastas vid otillräckligt lagersaldo
    @Test
    void shouldThrowWhenStockTooLow() throws Exception {

        Product saved = repository.save(
                new Product("Necklace-1", "En beskrivning till Necklace-1", BigDecimal.valueOf(100.00), 1, Category.NECKLACES, "/images/necklaces/necklace-1.avif")
        );

        String json = """
                [
                    {
                        "productId": %d,
                        "quantity": 5
                    }
                ]
                """.formatted(saved.getId());

        mvc.perform(post("/products/stock/decrease").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}

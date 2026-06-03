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
import se.iths.johanna.productservice.entity.Product;
import se.iths.johanna.productservice.repository.ProductRepository;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
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
                "A4 pappersark",
                "100 st",
                BigDecimal.valueOf(199.90),
                10
        );

        mvc.perform(post("/products")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))).contentType("application/json")
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("A4 pappersark"));
    }

    // Test - Lista alla produkter
    @Test
    void shouldListAllProducts() throws Exception {

        repository.save(new Product("A", "Description1", BigDecimal.valueOf(199.90), 10));
        repository.save(new Product("B", "Description2", BigDecimal.valueOf(299.90), 20));

        mvc.perform(get("/products").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    // Test - Hämta produkt (id)
    @Test
    void shouldGetProductById() throws Exception {

        Product saved = repository.save(
                new Product("A5 pappersark", "100 st", BigDecimal.valueOf(249.90), 10)
        );

        mvc.perform(get("/products/{id}", saved.getId()).with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("A5 pappersark"));
    }

    // Test - Ta bort produkt
    @Test
    void shouldDeleteProduct() throws Exception {

        Product saved = repository.save(
                new Product("Blyertspenna HB", "Description", BigDecimal.valueOf(9.90), 10)
        );

        mvc.perform(delete("/products/{id}", saved.getId()).with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(status().isNoContent());
    }

    // Test - Minska lagersaldo
    @Test
    void shouldDecreaseStock() throws Exception {

        Product saved = repository.save(
                new Product("Bläckpenna", "Svart 3-pack", BigDecimal.valueOf(99.90), 10)
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
    }

    // Test - Fel kastas vid otillräckligt lagersaldo
    @Test
    void shouldThrowWhenStockTooLow() throws Exception {

        Product saved = repository.save(
                new Product("A4 pappersark", "100 st", BigDecimal.valueOf(199.90), 1)
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

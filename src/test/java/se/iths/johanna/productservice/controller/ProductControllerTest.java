package se.iths.johanna.productservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    // Variabler
    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mvc;

    // Test - Skapa produkt (POST)
    @Test
    void shouldCreateProduct() throws Exception {

        ProductResponseDto response = new ProductResponseDto(
                1L, "Test", "Description", BigDecimal.valueOf(10), 5
        );

        when(service.createProduct(any(ProductRequestDto.class))).thenReturn(response);

        mvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "Test",
                                "description": "Description",
                                "price": 10,
                                "stock": 5
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    // Test - Hämta alla produkter (GET)
    @Test
    void shouldReturnAllProducts() throws Exception {
        ProductResponseDto product = new ProductResponseDto(
                1L, "Test", "Description", BigDecimal.valueOf(10), 5
        );

        when(service.getAllProducts()).thenReturn(List.of(product));

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test"));
    }

    // Test - Hämta produkt (id)(GET)
    @Test
    void shouldReturnProductById() throws Exception {

        ProductResponseDto product = new ProductResponseDto(
                1L, "Test", "Description", BigDecimal.valueOf(10), 5
        );

        when(service.getProductById(1L)).thenReturn(product);

        mvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }
}

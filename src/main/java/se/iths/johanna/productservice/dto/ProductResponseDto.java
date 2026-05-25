package se.iths.johanna.productservice.dto;

import java.math.BigDecimal;

public class ProductResponseDto {

    // Variabler
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    // Konstruktor
    public ProductResponseDto(Long id, String name, String description, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Metod - Hämtar produktens id
    public Long getId() {
        return id;
    }

    // Metod - Hämtar produktnamn
    public String getName() {
        return name;
    }

    // Metod - Hämtar produktbeskrivning
    public String getDescription() {
        return description;
    }

    // Metod - Hämtar produktens pris
    public BigDecimal getPrice() {
        return price;
    }

    // Metod - Hämtar lagersaldo
    public int getStock() {
        return stock;
    }
}


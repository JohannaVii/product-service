package se.iths.johanna.productservice.dto;

import java.math.BigDecimal;

public class ProductRequestDto {

    // Variabler
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    // Konstruktor - Tom
    public ProductRequestDto() {
    }

    // Konstruktor
    public ProductRequestDto(String name, String description, BigDecimal price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
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

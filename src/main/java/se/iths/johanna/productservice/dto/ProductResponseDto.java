package se.iths.johanna.productservice.dto;

import se.iths.johanna.productservice.entity.Category;

import java.math.BigDecimal;

public class ProductResponseDto {

    // Variabler
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private Category category;
    private String imageUrl;

    // Konstruktor
    public ProductResponseDto(Long id, String name, String description, BigDecimal price, int stock, Category category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl;
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

    // Metod - Hämtar kategori
    public Category getCategory() {
        return category;
    }

    // Metod - Hämtar bild (URL)
    public String getImageUrl() {
        return imageUrl;
    }
}


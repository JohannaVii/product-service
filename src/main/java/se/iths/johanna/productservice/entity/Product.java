package se.iths.johanna.productservice.entity;

import java.math.BigDecimal;

public class Product {

    // Attribut
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    // Konstruktor - Tom
    public Product() {
    }

    // Konstruktor
    public Product(Long id, String name, String description, BigDecimal price, int stock) {
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

    // Metod - Sätter id (test)
    public void setId(Long id) {
        this.id = id;
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

    // Metod - Uppdaterar lagersaldo
    public void setStock(int stock) {
        this.stock = stock;
    }
}

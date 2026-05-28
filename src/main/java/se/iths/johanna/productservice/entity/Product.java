package se.iths.johanna.productservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    // Attribut
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    // Konstruktor - Tom
    public Product() {
    }

    // Konstruktor
    public Product(String name, String description, BigDecimal price, int stock) {
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

    // Metod - Uppdaterar lagersaldo
    public void setStock(int stock) {
        this.stock = stock;
    }
}

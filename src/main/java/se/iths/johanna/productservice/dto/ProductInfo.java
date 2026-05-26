package se.iths.johanna.productservice.dto;

import java.math.BigDecimal;

public class ProductInfo {

    // Variabler
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

    // Konstruktor
    public ProductInfo(Long id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Metod - Hämtar produktens id
    public Long getId() {
        return id;
    }

    // Metod - Hämtar produktnamn
    public String getName() {
        return name;
    }

    // Metod - Hämtar produktens pris
    public BigDecimal getPrice() {
        return price;
    }

    // Metod - Hämtar kundens köpantal
    public int getQuantity() {
        return quantity;
    }
}

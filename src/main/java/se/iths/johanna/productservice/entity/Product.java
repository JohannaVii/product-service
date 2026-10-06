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

    @Enumerated(EnumType.STRING)
    private Category category;
    private String imageUrl;


    // Konstruktor - Tom
    public Product() {
    }

    // Konstruktor
    public Product(Long id, String name, String description, BigDecimal price, int stock, Category category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Konstruktor - Utan id
    public Product(String name, String description, BigDecimal price, int stock, Category category, String imageUrl) {
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

    // Metod - Sätter id (test)
    public void setId(Long id) {
        this.id = id;
    }

    // Metod - Hämtar produktnamn
    public String getName() {
        return name;
    }

    // Metod - Sätter produktnamn
    public void setName(String name) {
        this.name = name;
    }

    // Metod - Hämtar produktbeskrivning
    public String getDescription() {
        return description;
    }

    // Metod - Sätter produktbeskrivning
    public void setDescription(String description) {
        this.description = description;
    }

    // Metod - Hämtar produktens pris
    public BigDecimal getPrice() {
        return price;
    }

    // Metod - Sätter produktens pris
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Metod - Hämtar lagersaldo
    public int getStock() {
        return stock;
    }

    // Metod - Uppdaterar lagersaldo
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Metod - Hämtar kategori
    public Category getCategory() {
        return category;
    }

    // Metod - Sätter kategori
    public void setCategory(Category category) {
        this.category = category;
    }

    // Metod - Hämtar bild (URL)
    public String getImageUrl() {
        return imageUrl;
    }

    // Metod - Sätter bild(URL)
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

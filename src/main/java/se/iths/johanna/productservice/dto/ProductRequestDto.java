package se.iths.johanna.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductRequestDto {

    // Variabler
    @NotBlank(message = "Produktnamn-fältet får inte vara tomt!")
    private String name;
    @NotBlank(message = "Beskrivnings-fältet får inte vara tomt!")
    private String description;
    @NotNull(message = "Pris måste anges!")
    @DecimalMin(value = "0.01", message = "Ett pris måste vara större än 0!")
    private BigDecimal price;
    @Min(value = 0, message = "Lagersaldot kan inte vara negativt!")
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

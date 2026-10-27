package se.iths.johanna.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import se.iths.johanna.productservice.entity.Category;

import java.math.BigDecimal;

/**
 * Information för att skapa eller uppdatera en produkt
 *
 * @param name        anger produktens namn
 * @param description beskriver produkten
 * @param price       produktens pris
 * @param stock       anger produktens lagersaldo
 * @param category    anger vilken kategori produkten tillhör
 * @param imageUrl    anger länken till produktens bild
 */
public record ProductRequestDto(

        // Variabler
        @NotBlank(message = "Produktnamn-fältet får inte vara tomt!")
        String name,
        String description,
        @Positive(message = "Pris måste vara större än 0!")
        BigDecimal price,
        @PositiveOrZero(message = "Lagersaldot kan inte vara negativt!")
        int stock,
        Category category,
        String imageUrl

) {
}
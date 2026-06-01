package se.iths.johanna.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequestDto(

        // Variabler
        @NotBlank(message = "Produktnamn-fältet får inte vara tomt!")
        String name,
        String description,
        @Positive(message = "Pris måste vara större än 0!")
        BigDecimal price,
        @PositiveOrZero(message = "Lagersaldot kan inte vara negativt!")
        int stock

) {
}
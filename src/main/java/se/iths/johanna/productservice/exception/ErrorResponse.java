package se.iths.johanna.productservice.exception;

import java.util.List;

public class ErrorResponse {

    // Attribut
    private String message;
    private List<String> details;

    // Konstruktor
    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    // Metod - Hämtar felmeddelande
    public String getMessage() {
        return message;
    }

    // Metod - Hämtar detaljer
    public List<String> getDetails() {
        return details;
    }
}

package se.iths.johanna.productservice.exception;

import java.util.List;

/**
 * Information om fel som skickas tillbaka till klienten
 */
public class ErrorResponse {

    // Attribut
    private String message;
    private List<String> details;

    /**
     * Konstruktor som skapar ett felmeddelande
     *
     * @param message anger felmeddelandet
     * @param details innehåller detaljer om felet
     */
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

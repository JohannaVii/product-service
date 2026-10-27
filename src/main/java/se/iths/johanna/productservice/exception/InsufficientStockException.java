package se.iths.johanna.productservice.exception;

/**
 * Fel som uppstår när lagersaldot inte räcker
 */
public class InsufficientStockException extends RuntimeException {

    /**
     * Skapar ett fel vid otillräckligt lagersaldo
     *
     * @param message anger felmeddelandet
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}

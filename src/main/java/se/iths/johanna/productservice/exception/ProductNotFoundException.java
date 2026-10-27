package se.iths.johanna.productservice.exception;

/**
 * Fel som uppstår när en produkt inte hittas
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Skapar ett fel när produkten inte hittas
     *
     * @param s anger felmeddelandet
     */
    public ProductNotFoundException(String s) {
        super(s);
    }
}

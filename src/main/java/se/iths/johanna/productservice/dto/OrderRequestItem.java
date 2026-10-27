package se.iths.johanna.productservice.dto;

/**
 * Information om en produkt som ingår i en order
 */
public class OrderRequestItem {

    // Variabler
    private Long productId;
    private int quantity;

    /**
     * Tom konstruktor
     */
    public OrderRequestItem() {
    }

    /**
     * Konstruktor som skapar en produkt för en order
     *
     * @param productId används för att identifiera produkten
     * @param quantity  anger antal av produkten
     */
    public OrderRequestItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Metod - Hämtar produktens id (order)
    public Long getProductId() {
        return productId;
    }

    // Metod - Hämtar kundens köpantal
    public int getQuantity() {
        return quantity;
    }
}

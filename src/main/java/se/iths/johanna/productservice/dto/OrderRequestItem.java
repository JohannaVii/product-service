package se.iths.johanna.productservice.dto;

public class OrderRequestItem {

    // Variabler
    private Long productId;
    private int quantity;

    // Konstruktor - Tom
    public OrderRequestItem() {
    }

    // Konstruktor
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

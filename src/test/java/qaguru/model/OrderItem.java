package qaguru.model;

public class OrderItem {
    private String productName;
    private int quantity;
    private int price;

    public OrderItem() {
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
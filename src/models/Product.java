package models;

public class Product {
    private String name;
    private int quantity;
    private double retailPrice;
    private double discount;
    private String category;

    // Constructor ----------------------------------------------------

    public Product(String name, int quantity, double retailPrice, double discount, String category) {
        this.name = name;
        this.quantity = quantity;
        this.retailPrice = retailPrice;
        this.discount = discount;
        this.category = category;
    }

    // Helpers --------------------------------------------------------

    public void adjustQuantity(int amount) {
        int newQuantity = this.quantity + amount;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Cannot adjust quantity below 0");
        }
        this.quantity = newQuantity;
    }

    // Getters --------------------------------------------------------

    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getRetailPrice() {
        return retailPrice;
    }
    public double getDiscount() {
        return discount;
    }
    public String getCategory() {
        return category;
    }
    public double getDiscountedPrice() {
        return retailPrice * (1 - discount);
    }

    // Setters --------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
    public void setDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1.");
        }
        this.discount = discount;
    }
    public void setCategory(String category) {
        this.category = category;
    }       
}

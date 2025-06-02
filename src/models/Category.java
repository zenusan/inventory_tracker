package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String name;
    private String description;
    private List<Product> products;

    // Constructor ----------------------------------------------------

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    // Helpers --------------------------------------------------------

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        if (products.contains(product)) {
            throw new IllegalArgumentException("Product already exists in this category.");
        }

        products.add(product);
        if (!this.name.equals(product.getCategory())) {
            product.setCategory(this.name);
        } 
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        if (!products.contains(product)) {
            throw new IllegalArgumentException("Product does not exist in this category.");
        }

        products.remove(product);
        if (this.name.equals(product.getCategory())) {
            product.setCategory(null); 
        }
    }

    // Getters --------------------------------------------------------

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy to prevent external modification
    }

    // Setters --------------------------------------------------------

    public void setName(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        this.name = name;
    }
}

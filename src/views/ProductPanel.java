package views;

import models.Product;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ProductPanel extends JPanel {
    private Product product;
    
    public ProductPanel(Product product) {
        this.product = product;
        setLayout(new GridLayout(1, 5, 10, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Format currency and percentages
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        
        // Add the product information
        add(new JLabel(product.getName()));
        add(new JLabel(String.valueOf(product.getQuantity())));
        add(new JLabel(currencyFormat.format(product.getRetailPrice())));
        add(new JLabel(percentFormat.format(product.getDiscount())));
        add(new JLabel(currencyFormat.format(product.getDiscountedPrice())));
    }
    
    public void updateProduct(Product product) {
        this.product = product;
        removeAll();
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        
        add(new JLabel(product.getName()));
        add(new JLabel(String.valueOf(product.getQuantity())));
        add(new JLabel(currencyFormat.format(product.getRetailPrice())));
        add(new JLabel(percentFormat.format(product.getDiscount())));
        add(new JLabel(currencyFormat.format(product.getDiscountedPrice())));
        
        revalidate();
        repaint();
    }
}